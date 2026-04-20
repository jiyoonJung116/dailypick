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

    public void saveAlarmSetting(long userId, String isEmail, String isKakao, String alarmTime) {
        AlarmSettingDto alarmSetting = alarmSettingMapper.getAlarmSetting(userId);
        if (alarmSetting == null) {
            alarmSettingMapper.insertAlarmSetting(userId, isEmail, isKakao, alarmTime);
        } else {
            alarmSettingMapper.updateAlarmSetting(userId, isEmail, isKakao, alarmTime);
        }
    }
}
