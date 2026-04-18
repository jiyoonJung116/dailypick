package com.project.dailypick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.dailypick.dto.SummaryDataDto;

@Mapper
public interface SummaryDataMapper {
    List<SummaryDataDto> getSummaryList(@Param("categoryName") String categoryName, @Param("title") String title, @Param("content") String content);
}
