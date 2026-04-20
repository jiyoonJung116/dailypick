package com.project.dailypick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.dailypick.dto.SummaryDataDto;

@Mapper
public interface SummaryDataMapper {
    List<SummaryDataDto> getSummaryList(@Param("categoryId") long categoryId,
                                        @Param("title") String title,
                                        @Param("content") String content,
                                        @Param("offset") int offset,
                                        @Param("size") int size
    );
}
