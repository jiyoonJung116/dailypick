package com.project.dailypick.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dailypick.service.SubscriptionService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/subscription")
public class SubscriptionRestController {
    private final SubscriptionService subscriptionService;

    public SubscriptionRestController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("info")
    public Map<String, Object> getUserSubscription(@RequestParam(name = "userId") long userId) {
        Map<String, Object> result = new HashMap<>();

        try {
            result.put("status", "success");
            result.put("subscription_info", subscriptionService.getUserSubscription(userId));
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        
        return result;
    }
    
}
