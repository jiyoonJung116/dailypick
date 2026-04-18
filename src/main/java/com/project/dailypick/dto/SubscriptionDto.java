package com.project.dailypick.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    private long id;
    private long userId;
    private String startDate;
    private String endDate;
    private String isUnpaid;
}
