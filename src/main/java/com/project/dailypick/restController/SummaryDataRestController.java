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
    public Map<String, Object> getSummaryList(@RequestParam(name = "categoryId") int categoryId,
                                                @RequestParam(name = "title") String title,
                                                @RequestParam(name = "content") String content,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", "success");
            result.put("summary_list", summaryDataService.getSummaryList(categoryId, title, content, page, size));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }

        return result;
    }  
}
