package com.sau.dims.controller;

import com.sau.dims.dto.AuthResponseDTO;
import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;
import com.sau.dims.security.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String  register(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, Model model) throws IOException {
        System.out.println("func");
        if(result.hasErrors()){
            model.addAttribute("user",user);
            model.addAttribute("error",result.getAllErrors());
            return "/auth/signupPage";
        }

        boolean isRegist = authService.register(user);
        System.out.println(isRegist);
        if(!isRegist){
            System.out.println("hataaaa");
            ObjectError error = new ObjectError("globalError", "Username already exist!");
//            model.addAttribute("error",error);
            model.addAttribute("globalError","Username already exist!");
            result.addError(error);
            return "/auth/signupPage";
        }

        return "redirect:/login";
    }


    @PostMapping("/login")
    @ResponseBody
    public void login(@ModelAttribute LoginDTO loginDTO, HttpServletResponse response) throws IOException {
        AuthResponseDTO authResponseDTO = authService.login(loginDTO);
        Cookie authCookie = new Cookie("Authorization", authResponseDTO.getAccessToken());
        authCookie.setHttpOnly(true);
        authCookie.setPath("/");
        response.addCookie(authCookie);
        response.sendRedirect("/index");
    }



}
