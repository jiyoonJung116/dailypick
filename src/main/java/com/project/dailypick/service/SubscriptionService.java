package com.project.dailypick.service;

import org.springframework.stereotype.Service;

import com.project.dailypick.dto.SubscriptionDto;
import com.project.dailypick.mapper.SubscriptionMapper;

@Service
public class SubscriptionService {
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionService(SubscriptionMapper subscriptionMapper) {
        this.subscriptionMapper = subscriptionMapper;
    }

    public SubscriptionDto getUserSubscription(long userId) {
        return subscriptionMapper.getUserSubscription(userId);
    }
}
