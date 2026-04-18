package com.project.dailypick.restController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.service.SummaryDataService;

@RestController
@RequestMapping("api/summary")
public class SummaryDataRestController {
    private final SummaryDataService summaryDataService;

    public SummaryDataRestController(SummaryDataService summaryDataService) {
        this.summaryDataService = summaryDataService;
    }

    @PostMapping("list")
    public Map<String, Object> getSummaryList(@RequestParam(name = "categoryName", required = false) String categoryName,
                                                @RequestParam(name = "title", required = false) String title,
                                                @RequestParam(name = "content", required = false) String content) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", "success");
            result.put("category_list", summaryDataService.getSummaryList(categoryName, title, content));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }

        return result;
    }  
}
