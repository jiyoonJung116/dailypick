package com.project.dailypick.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.dto.AlarmSettingDto;
import com.project.dailypick.service.AlarmSettingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/alarm")
public class AlarmSettingRestController {
    private final AlarmSettingService alarmSettingService;

    public AlarmSettingRestController(AlarmSettingService alarmSettingService) {
        this.alarmSettingService = alarmSettingService;
    }

    @PostMapping("setting")
    public Map<String, Object> getAlarmSetting(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpSession session = request.getSession();

        try {
            if (session == null || session.getAttribute("userId") == null) {
                result.put("status", "error");
                result.put("message", "로그인이 필요합니다.");
                return result;
            }
            
            Long userId = Long.parseLong(session.getAttribute("userId").toString());
            result.put("status", "success");
            result.put("alarm_info", alarmSettingService.getAlarmSetting(userId));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        
        return result;
    }

    @PostMapping("save")
    public Map<String, Object> updateAlarm(HttpSession session, 
                                            @RequestParam(name = "type") String type, 
                                            @RequestParam(name = "status") String status) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            Map<String, Object> result = new HashMap<>();
            result.put("status", "error");
            result.put("message", "로그인이 필요합니다.");

            return result;
        }

        AlarmSettingDto alarmDto = alarmSettingService.getAlarmSetting(userId);
        
        String isEmail = (alarmDto != null) ? alarmDto.getIsEmail() : "F";
        String isKakao = (alarmDto != null) ? alarmDto.getIsKakao() : "F";
        String alarmTime = (alarmDto != null) ? alarmDto.getAlarmTime() : "09:00";

        if ("email".equals(type)) {
            isEmail = status;
        } else if ("kakao".equals(type)) {
            isKakao = status;
        }

        try {
            alarmSettingService.saveAlarmSetting(userId, isEmail, isKakao, alarmTime);
            Map<String, Object> result = new HashMap<>();
            result.put("status", "success");

            return result;
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            result.put("status", "error");
            result.put("message", e.getMessage());

            return result;
        }
    }
    
}
