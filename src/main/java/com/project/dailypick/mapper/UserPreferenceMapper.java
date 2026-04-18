package com.project.dailypick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.dailypick.dto.UserPreferenceDto;

@Mapper
public interface UserPreferenceMapper {
    UserPreferenceDto getUserPreference(@Param("userId") long useId);
}
