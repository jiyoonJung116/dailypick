package com.project.dailypick.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.dailypick.dto.SendLogDto;

@Mapper
public interface SendLogMapper {
    List<SendLogDto> getSendLogList(Map<String, Object> param);

    SendLogDto getSendLogDetail(@Param("userId") Long userId, @Param("logId") Long logId);
}
