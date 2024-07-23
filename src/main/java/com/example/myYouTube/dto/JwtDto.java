package com.example.myYouTube.dto;

import com.example.myYouTube.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
    private Integer id;
    private String username;
    private ProfileRole role;

    public JwtDto(Integer id) {
        this.id = id;
    }

    public JwtDto(Integer id, String username, ProfileRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
