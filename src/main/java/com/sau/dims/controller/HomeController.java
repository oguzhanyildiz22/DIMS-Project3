package com.sau.dims.controller;

import com.sau.dims.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(){
        return "auth/login";
    }

    @GetMapping("/login")
    public String returnLoginAfterSignUp(){
        return "auth/login";
    }

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }


    @GetMapping("/sign-up")
    public String getSignUp(Model model){
        model.addAttribute("user",new UserDTO());
        return "auth/signupPage";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about/index";
    }

    @GetMapping("/logout")
    public String logout(){
        return "auth/login";
    }

}
