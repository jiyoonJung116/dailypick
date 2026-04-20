package com.project.dailypick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.dailypick.dto.UserPreferenceDto;

@Mapper
public interface UserPreferenceMapper {
    List<UserPreferenceDto> getUserPreference(@Param("userId") long useId);
}
