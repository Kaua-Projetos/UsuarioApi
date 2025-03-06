package com.example.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageView {


    @GetMapping
    public String loginPage(){
        return "login";
    }

}
