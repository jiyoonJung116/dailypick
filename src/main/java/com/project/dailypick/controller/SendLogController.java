package com.project.dailypick.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dailypick.dto.SendLogDto;
import com.project.dailypick.service.SendLogService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("log")
public class SendLogController {
    private final SendLogService sendLogService;

    public SendLogController(SendLogService sendLogService) {
        this.sendLogService = sendLogService;
    }

    @GetMapping("list")
    public String sendLog(Model model, 
                            HttpSession session,
                            @RequestParam(name = "isSuccess", defaultValue = "ALL") String isSuccess,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            Long userId = Long.parseLong(session.getAttribute("userId").toString());
            List<SendLogDto> sendLogList = sendLogService.getSendLogList(userId, isSuccess, page, size);
            model.addAttribute("send_log_list", sendLogList);
        } catch (Exception e) {
            System.out.println("Error in sendLog: " + e.getMessage());
        }

        return "send_log";
    }

    @ResponseBody
    @GetMapping("api/list")
    public List<SendLogDto> getLogData(HttpSession session,
                                        @RequestParam(name = "isSuccess", defaultValue = "ALL") String isSuccess,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "15") int size) {
        Long userId = Long.parseLong(session.getAttribute("userId").toString());
        return sendLogService.getSendLogList(userId, isSuccess, page, size);
    }
    
}
