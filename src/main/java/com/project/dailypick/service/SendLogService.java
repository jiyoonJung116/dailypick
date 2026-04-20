package com.project.dailypick.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.dailypick.dto.SendLogDto;
import com.project.dailypick.mapper.SendLogMapper;

@Service
public class SendLogService {
    private final SendLogMapper sendLogMapper;

    public SendLogService(SendLogMapper sendLogMapper) {
        this.sendLogMapper = sendLogMapper;
    }

    public List<SendLogDto> getSendLogList(Long userId, String isSuccess, int page, int size) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("isSuccess", isSuccess);
        param.put("page", page);
        param.put("size", size);
        param.put("offset", page * size);
        
        return sendLogMapper.getSendLogList(param);
    }
}
