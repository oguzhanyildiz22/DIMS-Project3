package com.sau.dims.controller;

import com.sau.dims.dto.LoginDTO;
import com.sau.dims.dto.UserDTO;
import com.sau.dims.security.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String  register(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, Model model) throws IOException {
        if(result.hasErrors()){
            model.addAttribute("user",user);
            model.addAttribute("error",result.getAllErrors());
            return "/auth/signupPage";
        }

        boolean isRegist = authService.register(user);
        if(!isRegist){
            ObjectError error = new ObjectError("globalError", "Username already exist!");
            model.addAttribute("globalError","Username already exist!");
            result.addError(error);
            return "/auth/signupPage";
        }

        return "redirect:/login";
    }


    @PostMapping("/login")
    @ResponseBody
    public void login(@ModelAttribute LoginDTO loginDTO,
                      HttpServletResponse response,
                      HttpServletRequest request) throws IOException {
        Cookie cookie = new Cookie("Authorization", authService.login(loginDTO));
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/index");
//        return "redirect:/index";
    }



}
