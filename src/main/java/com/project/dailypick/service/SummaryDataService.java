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

    public List<SummaryDataDto> getSummaryList(long categoryId, String title, String content, int page, int size) {
        return summaryDataMapper.getSummaryList(categoryId, title, content, page, size);
    }
}
