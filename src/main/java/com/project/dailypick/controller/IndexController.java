package com.project.dailypick.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.project.dailypick.dto.UserDto;
import com.project.dailypick.dto.UserPreferenceDto;
import com.project.dailypick.service.CategoryService;

import jakarta.servlet.http.HttpSession;
import tools.jackson.databind.ObjectMapper;


@Controller
public class IndexController {
    private final CategoryService categoryService;

    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String mainPage(Model model, HttpSession session, @SessionAttribute(name = "userId", required = false) Long userId) {
        String loginInfoJson = (String) session.getAttribute("login_info");

        if (userId == null) {
            model.addAttribute("isLogin", false);
            return "main";
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UserDto user = objectMapper.readValue(loginInfoJson, UserDto.class);
            
            model.addAttribute("isLogin", true);
            model.addAttribute("userName", user.getUserName());

            List<UserPreferenceDto> hasCategory = categoryService.gethasUserCategory(userId);
            if (hasCategory.isEmpty()) {
                return "redirect:/category/list";
            }
        } catch (Exception e) {
            model.addAttribute("isLogin", false);
            return "main";
        }

        return "main";
    }
    
}
