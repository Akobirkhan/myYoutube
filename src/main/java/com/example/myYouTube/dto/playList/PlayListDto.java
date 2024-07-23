package com.example.myYouTube.dto.playList;

import com.example.myYouTube.dto.channel.ChannelDto;
import com.example.myYouTube.dto.profile.ProfileDto;
import com.example.myYouTube.dto.video.VideoDto;
import com.example.myYouTube.enums.PlayListStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayListDto {
    private Long id;
    private String name;
    private String description;
    private PlayListStatus status;
    private String chanelId;
    private ChannelDto chanel;
    private Long profileId;
    private ProfileDto profile;
    private int orderNumber;
    private LocalDateTime createdDate;
    private Long videoCount;
    private Integer totalViewCount;
    private List<VideoDto> videoList;
}
