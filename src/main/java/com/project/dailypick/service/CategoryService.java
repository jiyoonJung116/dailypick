package com.project.dailypick.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dailypick.dto.CategoryDto;
import com.project.dailypick.dto.UserPreferenceDto;
import com.project.dailypick.mapper.CategoryMapper;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getCategoryList(String categoryName) {
        return categoryMapper.getCategoryList(categoryName);
    }

    public List<UserPreferenceDto> gethasUserCategory(long userIdx) {
        return categoryMapper.gethasUserCategory(userIdx);
    }

    public long insertUserCategory(List<UserPreferenceDto> userPreferenceDto) {
        return categoryMapper.insertUserCategory(userPreferenceDto);
    }
}
