package com.project.dailypick.restController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.dto.SendLogDto;
import com.project.dailypick.service.SendLogService;

@RestController
@RequestMapping("api/log")
public class SendLogRestController {
    private final SendLogService sendLogService;

    public SendLogRestController(SendLogService sendLogService) {
        this.sendLogService = sendLogService;
    }

    @PostMapping("list")
    public Map<String, Object> getSendLogList(@RequestParam(value = "userName", required = false) String userName,
                                                @RequestParam(value = "email", required = false) String email,
                                                @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                @RequestParam(value = "isSuccess", required = false) String isSuccess,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", "success");
            result.put("send_log_list", sendLogService.getSendLogList(userName, email, phoneNumber, isSuccess, page, size));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        
        return result;
    }
}
