package com.project.dailypick.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDataDto {
    private long id;
    private long categoryId;
    private String title;
    private String content;
    private String createDate;
}
