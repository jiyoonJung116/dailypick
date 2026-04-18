package com.project.dailypick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("log")
public class SendLogController {
    @GetMapping("list")
    public String sendLog() {
        return "send_log";
    }
    
}
