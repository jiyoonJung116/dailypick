package com.project.dailypick.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.dailypick.dto.SubscriptionDto;

@Mapper
public interface SubscriptionMapper {
    SubscriptionDto getUserSubscription(@Param("userId") long useId);
}
