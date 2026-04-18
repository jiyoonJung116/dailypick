package com.project.dailypick.restController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.dto.UserDto;
import com.project.dailypick.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import tools.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("api/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("info")
    public Map<String, Object> getUserByUserId(HttpServletRequest request) {
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
            result.put("user_info", userService.getUserById(userId));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    

    @PostMapping("join")
    public Map<String, Object> userCreate(@ModelAttribute UserDto userDto) {
        Map<String, Object> result = new HashMap<>();

        if (userDto.getUserId() == null || userDto.getUserId().isEmpty()) {
            result.put("status", "error");
            result.put("message", "사용자 ID는 필수입니다.");
            return result;
        }

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            result.put("status", "error");
            result.put("message", "비밀번호는 필수입니다.");
            return result;
        }

        if (userDto.getUserName() == null || userDto.getUserName().isEmpty()) {
            result.put("status", "error");
            result.put("message", "사용자 이름은 필수입니다.");
            return result;
        }

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            result.put("status", "error");
            result.put("message", "이메일은 필수입니다.");
            return result;
        }

        if (userDto.getPhoneNumber() == null || userDto.getPhoneNumber().isEmpty()) {
            result.put("status", "error");
            result.put("message", "전화번호는 필수입니다.");
            return result;
        }

        try {   
            Long userIdx = userService.createUser(userDto);
            result.put("status", "success");
            result.put("userIdx", userIdx);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        
        return result;
    }

    @PostMapping("login")
    public Map<String, Object> doLogin(HttpServletRequest request,
                                        @ModelAttribute UserDto userDto) {
        Map<String, Object> result = new HashMap<>();
        HttpSession session = request.getSession();

        if (userDto == null || userDto.getUserId() == null || userDto.getPassword() == null) {
            result.put("status", "error");
            result.put("message", "잘못된 요청입니다.");
            return result;
        }

        try {
            long userIdx = userService.login(userDto.getUserId(), userDto.getPassword());
            result.put("status", userIdx != 0 ? "success" : "error");

            if (userIdx == 0) {
                result.put("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            } else {
                // 로그인 정보 저장
                UserDto user = userService.getUserById(userIdx);
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(user);

                session.setAttribute("login_info", jsonData);
                session.setAttribute("userId", userIdx);

                result.put("login_info", user);
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        
        return result;
    }
}
