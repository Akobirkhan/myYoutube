package com.example.myYouTube.dto.email;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailHistoryFilterDto {
    private String email;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;
}
