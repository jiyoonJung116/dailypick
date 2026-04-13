package com.project.dailypick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("summary")
public class SummaryDataController {
    @GetMapping("list")
    public String newsSummary() {
        return "news_summary";
    }
}
