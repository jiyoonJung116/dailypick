package com.project.dailypick.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dailypick.dto.UserPreferenceDto;
import com.project.dailypick.mapper.UserPreferenceMapper;

@Service
public class UserPreferenceService {
    private final UserPreferenceMapper userPreferenceMapper;

    public UserPreferenceService (UserPreferenceMapper userPreferenceMapper) {
        this.userPreferenceMapper = userPreferenceMapper;
    }

    public List<UserPreferenceDto> getUserPreference(long userId) {
        return userPreferenceMapper.getUserPreference(userId);
    }
}
