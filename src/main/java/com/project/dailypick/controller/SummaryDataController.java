package com.project.dailypick.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dailypick.dto.CategoryDto;
import com.project.dailypick.dto.SummaryDataDto;
import com.project.dailypick.service.CategoryService;
import com.project.dailypick.service.SummaryDataService;

@Controller
@RequestMapping("summary")
public class SummaryDataController {
    private final SummaryDataService summaryDataService;
    private final CategoryService categoryService;

    public SummaryDataController(SummaryDataService summaryDataService, CategoryService categoryService) {
        this.summaryDataService = summaryDataService;
        this.categoryService = categoryService;
    }

    @GetMapping("list")
    public String newsSummary(Model model, 
                                @RequestParam(name = "categoryId", defaultValue = "0") int categoryId,
                                @RequestParam(name = "title", defaultValue = "") String title,
                                @RequestParam(name = "content", defaultValue = "") String content,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            List<CategoryDto> categoryList = categoryService.getCategoryList("");
            model.addAttribute("category_list", categoryList);

            List<SummaryDataDto> summaryList = summaryDataService.getSummaryList(categoryId, title, content, page, size);
            model.addAttribute("total_count", summaryList.size());
            model.addAttribute("summary_list", summaryList);

            // 검색 조건 유지
            model.addAttribute("select_category_id", categoryId);
            model.addAttribute("search_title", title);
            model.addAttribute("search_content", content);
        } catch (Exception e) {
            System.out.println("Error in newsSummary: " + e.getMessage());
        }

        return "news_summary";
    }

    @ResponseBody
    @GetMapping("api/list")
    public List<SummaryDataDto> getNewsListData(
            @RequestParam(name = "categoryId") int categoryId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content) {
        return summaryDataService.getSummaryList(categoryId, title, content, 0, 10);
    }
}
