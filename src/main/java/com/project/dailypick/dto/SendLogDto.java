package com.project.dailypick.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendLogDto {
    private long id;
    private long userId;
    private String phoneNumber;
    private String email;
    private String alarmTime;
    private String isSuccess;
    private String deliveryDate;
}
