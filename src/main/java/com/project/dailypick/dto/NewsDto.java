package com.project.dailypick.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private long id;
    private long categoryId;
    private long summaryId;
    private String title;
    private String content;
    private String link;
    private String createDate;
}
