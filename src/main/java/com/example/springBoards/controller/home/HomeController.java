package com.example.springBoards.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index/index";
    }

    @GetMapping("/register")
    public String register() {
        return "index/register";
    }

    @GetMapping("/login")
    public String login() {
        return "index/login";
    }
}
