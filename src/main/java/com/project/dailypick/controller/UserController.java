package com.project.dailypick.controller;

import com.project.dailypick.service.AlarmSettingService;
import com.project.dailypick.service.CategoryService;
import com.project.dailypick.service.UserPreferenceService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.project.dailypick.dto.CategoryDto;
import com.project.dailypick.dto.UserDto;
import com.project.dailypick.dto.UserPreferenceDto;

import jakarta.servlet.http.HttpSession;
import tools.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("user")
public class UserController {
    private final CategoryService categoryService;
    private final UserPreferenceService userPreferenceService;
    private final AlarmSettingService alarmSettingService;

    UserController(CategoryService categoryService, UserPreferenceService userPreferenceService, AlarmSettingService alarmSettingService) {
        this.categoryService = categoryService;
        this.userPreferenceService = userPreferenceService;
        this.alarmSettingService = alarmSettingService;
    }

    @GetMapping("join")
    public String pageJoin() {
        return "join";
    }

    @GetMapping("login")
    public String pageLogin() {
        return "login";
    }

    @GetMapping("info")
    public String userInfoPage(HttpSession session, @SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        if (userId == null) {
            return "redirect:/user/login";
        }

        try {
            // 로그인 유저 정보 조회
            String loginInfoJson = (String) session.getAttribute("login_info");
            ObjectMapper objectMapper = new ObjectMapper();
            UserDto user = objectMapper.readValue(loginInfoJson, UserDto.class);
            model.addAttribute("user_info", user);

            // 카테고리 목록 조회
            List<CategoryDto> categoryList =  categoryService.getCategoryList("");
            model.addAttribute("category_list", categoryList);

            // 내 카테고리 목록 조회
            List<UserPreferenceDto> userPrefenceList = userPreferenceService.getUserPreference(user.getId());
            List<Long> userCategoryList = new ArrayList<>();
            for (UserPreferenceDto userPrefence : userPrefenceList) {
                userCategoryList.add(userPrefence.getCategoryId());
            }
            model.addAttribute("user_category_list", userCategoryList);

            // 알림 설정 조회
            model.addAttribute("alarm_setting", alarmSettingService.getAlarmSetting(user.getId()));
        } catch (Exception e) {
            return "redirect:/user/login";
        }
        
        return "info";
    }

    @GetMapping("logout")
    public String pageLogout(HttpSession session) {
        session.invalidate();

        return "main";
    }
}
