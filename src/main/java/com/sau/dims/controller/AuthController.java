package com.sau.dims.controller;

import com.sau.dims.dto.AuthResponseDTO;
import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;
import com.sau.dims.security.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public void  register(@ModelAttribute UserDTO user,HttpServletResponse response) throws IOException {
        authService.register(user);
        response.sendRedirect("/login");
    }


    @PostMapping("/login")
    public void login(@ModelAttribute LoginDTO loginDTO, HttpServletResponse response) throws IOException {
        AuthResponseDTO authResponseDTO = authService.login(loginDTO);
        Cookie authCookie = new Cookie("Authorization", authResponseDTO.getAccessToken());
        authCookie.setHttpOnly(true);
        authCookie.setPath("/");
        response.addCookie(authCookie);
        response.sendRedirect("/index");
    }



}
