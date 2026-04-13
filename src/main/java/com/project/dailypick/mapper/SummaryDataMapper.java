package com.project.dailypick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dailypick.dto.SummaryDataDto;

@Mapper
public interface SummaryDataMapper {
    List<SummaryDataDto> getSummaryList(String categoryName, String title, String content);
}
