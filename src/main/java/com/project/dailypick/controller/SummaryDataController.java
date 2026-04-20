package com.project.dailypick.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dailypick.dto.CategoryDto;
import com.project.dailypick.service.CategoryService;
import com.project.dailypick.service.SummaryDataService;

@Controller
@RequestMapping("summary")
public class SummaryDataController {
    private final CategoryService categoryService;

    public SummaryDataController(SummaryDataService summaryDataService, CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("list")
    public String newsSummary(Model model, 
                                @RequestParam(name = "categoryId", defaultValue = "0") int categoryId,
                                @RequestParam(name = "title", defaultValue = "") String title,
                                @RequestParam(name = "content", defaultValue = "") String content,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            List<CategoryDto> categoryList = categoryService.getCategoryList("");
            model.addAttribute("category_list", categoryList);
        } catch (Exception e) {
            System.out.println("Error in newsSummary: " + e.getMessage());
        }

        return "news_summary";
    }
}
