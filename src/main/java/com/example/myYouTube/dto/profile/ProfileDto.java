package com.example.myYouTube.dto.profile;


import com.example.myYouTube.dto.attach.AttachDto;
import com.example.myYouTube.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String photoId;
    private AttachDto attach;
    private LocalDateTime createdDate;
    private ProfileRole role;
    private String jwt;
}
