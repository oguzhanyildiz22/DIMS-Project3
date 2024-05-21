package com.sau.dims.security;

import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;
import com.sau.dims.model.Adviser;
import com.sau.dims.repository.AdviserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AdviserRepository adviserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    public boolean  register(UserDTO userDTO) throws IOException {
        if (adviserRepository.existsByUsername(userDTO.getUsername())){
           return false;
        }
        Adviser user = new Adviser();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setRole(userDTO.getRole());
        user.setDepartment(userDTO.getDepartment());
        user.setImgURL(userDTO.getPicture().getBytes());

        adviserRepository.save(user);

        return true;

    }

    public String login(LoginDTO request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));

        String token = jwtGenerator.generateToken(authentication);
        System.out.printf("token: %s\n",token);

        return token;
    }
}
