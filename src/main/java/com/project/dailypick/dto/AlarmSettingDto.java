package com.project.dailypick.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmSettingDto {
    private long userId;
    private String isEmail;
    private String isKakao;
    private String alarmTime;
}
