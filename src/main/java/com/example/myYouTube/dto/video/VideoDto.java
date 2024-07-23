package com.example.myYouTube.dto.video;


import com.example.myYouTube.dto.attach.AttachDto;
import com.example.myYouTube.dto.category.CategoryDto;
import com.example.myYouTube.dto.channel.ChannelDto;
import com.example.myYouTube.enums.VideoStatus;
import com.example.myYouTube.enums.VideoType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDto {

    private String id;
    private String title;
    private String description;
    private String previewAttachId;
    private AttachDto previewAttach;
    private String attachId;
    private AttachDto attach;
    private Integer categoryId;
    private CategoryDto category;
    private VideoStatus status;
    private VideoType type;
    private String channelId;
    private ChannelDto channel;
    private Integer viewCount;
    private Integer sharedCount;
    private Integer likeCount;
    private Integer dislikeCount;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;

}
