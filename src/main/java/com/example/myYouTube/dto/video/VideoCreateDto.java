package com.example.myYouTube.dto.video;


import com.example.myYouTube.enums.VideoStatus;
import com.example.myYouTube.enums.VideoType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoCreateDto {

    private String title;
    private String description;
    private VideoStatus status;
    private VideoType type;
    private String previewAttachId;
    private String attachId;
    private Integer categoryId;
    private LocalDateTime createdDate;
    private String channelId;
}
