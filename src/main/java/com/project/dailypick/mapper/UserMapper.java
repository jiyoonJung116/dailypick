package com.project.dailypick.mapper;

import com.project.dailypick.dto.UserDto;
import com.project.dailypick.dto.UserPreferenceDto;

public interface UserMapper {
    UserDto getUserById(long id);

    UserDto getUserByUserId(String userId);

    UserPreferenceDto getUserPreferenceByUserId();

    long createUser(UserDto userDto);
}
