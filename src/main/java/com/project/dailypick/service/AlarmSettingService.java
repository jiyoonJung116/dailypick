package com.project.dailypick.service;

import org.springframework.stereotype.Service;

import com.project.dailypick.dto.AlarmSettingDto;
import com.project.dailypick.mapper.AlarmSettingMapper;

@Service
public class AlarmSettingService {
    private final AlarmSettingMapper alarmSettingMapper;

    public AlarmSettingService(AlarmSettingMapper alarmSettingMapper) {
        this.alarmSettingMapper = alarmSettingMapper;
    }

    public AlarmSettingDto getAlarmSetting(long userId) {
        return alarmSettingMapper.getAlarmSetting(userId);
    }
}
