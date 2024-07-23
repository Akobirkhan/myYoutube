package com.example.myYouTube.dto.video;


import com.example.myYouTube.dto.attach.AttachDto;
import com.example.myYouTube.enums.EmotionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoLikeDto {
    private String id;
    private Long userId;
    private String videoId;
    private VideoDto video;
    private AttachDto previewAttachId;
    private EmotionStatus reaction;
    private LocalDateTime createdDate;
}
