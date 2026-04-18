package com.project.dailypick.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.service.AlarmSettingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

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
    
}
