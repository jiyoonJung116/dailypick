package com.project.dailypick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.dailypick.dto.AlarmSettingDto;

@Mapper
public interface AlarmSettingMapper {
    AlarmSettingDto getAlarmSetting(@Param("userId") long useId);
}
