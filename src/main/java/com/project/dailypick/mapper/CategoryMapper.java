package com.project.dailypick.mapper;

import java.util.List;

import com.project.dailypick.dto.CategoryDto;
import com.project.dailypick.dto.UserPreferenceDto;

public interface CategoryMapper {
    List<CategoryDto> getCategoryList(String categoryName);

    List<UserPreferenceDto> gethasUserCategory(long userIdx);

    long insertUserCategory(List<UserPreferenceDto> userPreferenceDto);

    void deleteUserPreferences(Long userId);
}
