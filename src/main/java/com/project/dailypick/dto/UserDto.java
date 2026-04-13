package com.project.dailypick.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
    private String email;
    private String isSubscribe;
    private String createDate;
}
