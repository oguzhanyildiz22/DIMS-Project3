package com.sau.dims.controller;

import com.sau.dims.dto.AuthResponseDTO;
import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;
import com.sau.dims.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        return authService.register(user);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        HttpHeaders headers = new HttpHeaders();

        AuthResponseDTO authResponseDTO = authService.login(loginDTO);
        headers.add("Authorization",authResponseDTO.getAccessToken());

        return ResponseEntity.ok().headers(headers).body("redirect:index");

    }

}
