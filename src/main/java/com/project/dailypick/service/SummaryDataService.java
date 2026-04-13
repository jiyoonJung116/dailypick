package com.project.dailypick.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dailypick.dto.SummaryDataDto;
import com.project.dailypick.mapper.SummaryDataMapper;

@Service
public class SummaryDataService {
    private final SummaryDataMapper summaryDataMapper;

    public SummaryDataService(SummaryDataMapper summaryDataMapper) {
        this.summaryDataMapper = summaryDataMapper;
    }

    public List<SummaryDataDto> getSummaryList(String categoryName, String title, String content) {
        return summaryDataMapper.getSummaryList(categoryName, title, content);
    }
}
