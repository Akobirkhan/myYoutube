package com.example.myYouTube.dto.comment;

import com.example.myYouTube.dto.profile.ProfileDto;
import com.example.myYouTube.dto.video.VideoDto;
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
public class CommentDto {
    private String id;
    private ProfileDto profile;
    private Long profileId;
    private String content;
    private String videoId;
    private VideoDto video;
    private String replId;
    private LocalDateTime createdDate;
    private Integer likeCount;
    private Integer dislikeCount;
}
