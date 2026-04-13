package com.project.dailypick.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.project.dailypick.dto.UserPreferenceDto;
import com.project.dailypick.service.CategoryService;


@Controller
public class IndexController {
    private final CategoryService categoryService;

    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String mainPage(@SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/user/login";
        }

        List<UserPreferenceDto> hasCategory = categoryService.gethasUserCategory(userId);

        if (hasCategory.isEmpty()) {
            return "redirect:/category/list";
        }

        return "main";
    }
    
}
