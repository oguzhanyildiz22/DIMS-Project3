package com.sau.dims.security;

import com.sau.dims.model.Adviser;
import com.sau.dims.repository.AdviserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdviserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Adviser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found!"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .accountLocked(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }
}
