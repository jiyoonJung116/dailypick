package com.project.dailypick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.project.dailypick.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import tools.jackson.databind.ObjectMapper;

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

    @GetMapping("info")
    public String userInfoPage(HttpSession session, @SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        if (userId == null) {
            return "redirect:/user/login";
        }

        try {
            String loginInfoJson = (String) session.getAttribute("login_info");
            ObjectMapper objectMapper = new ObjectMapper();
            UserDto user = objectMapper.readValue(loginInfoJson, UserDto.class);
            model.addAttribute("user", user);
        } catch (Exception e) {
            return "redirect:/user/login";
        }
        
        return "info";
    }

    @GetMapping("logout")
    public String pageLogout(HttpSession session) {
        session.invalidate();

        return "main";
    }
}
