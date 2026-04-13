package com.project.dailypick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping("join")
    public String pageJoin() {
        return "join";
    }

    @GetMapping("login")
    public String pageLogin() {
        return "login";
    }
}
