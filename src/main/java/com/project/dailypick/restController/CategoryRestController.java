package com.project.dailypick.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.dto.UserPreferenceDto;
import com.project.dailypick.service.CategoryService;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("api/category")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("list")
    public Map<String, Object> getCategoryList(@RequestParam(name = "categoryName", required = false) String categoryName) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", "success");
            result.put("category_list", categoryService.getCategoryList(categoryName));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }

        return result;
    }  

    @PostMapping("insert")
    public Map<String, Object> insertUserCategory(HttpSession session,
                                                @RequestParam(name = "categories") List<Long> categories) {

        Map<String, Object> result = new HashMap<>();

        try {
            Long userId = (Long) session.getAttribute("userId");

            List<UserPreferenceDto> list = new ArrayList<>();

            for (Long categoryId : categories) {
                UserPreferenceDto dto = new UserPreferenceDto();
                dto.setUserIdx(userId);
                dto.setCategoryId(categoryId);
                list.add(dto);
            }

            categoryService.insertUserCategory(list);

            result.put("status", "success");

        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }

        return result;
    }
}
