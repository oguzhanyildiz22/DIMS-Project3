package com.sau.dims.controller;

import org.springframework.stereotype.Controller;
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
    public String getSignUp(){
        return "auth/signUpPage";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about/index";
    }


}
