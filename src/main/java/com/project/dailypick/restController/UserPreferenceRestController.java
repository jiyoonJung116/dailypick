package com.project.dailypick.restController;

import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.service.UserPreferenceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/user/preference")
public class UserPreferenceRestController {
    private UserPreferenceService userPreferenceService;

    private UserPreferenceRestController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    @PostMapping("info")
    public Map<String, Object> getUserPreference(HttpServletRequest request) {
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
            result.put("preference_info", userPreferenceService.getUserPreference(userId));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    
}
