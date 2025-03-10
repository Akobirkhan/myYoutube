package com.example.myYouTube.service;


import com.example.myYouTube.entity.ViewCountEntity;
import com.example.myYouTube.repository.ViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ViewCountService {

    private final ViewCountRepository viewCountRepository;

    public void create(String videoId, Integer count, Long profileId) {
        ViewCountEntity entity = new ViewCountEntity();
        entity.setVideoId(videoId);
        entity.setProfileId(profileId);
        entity.setCount(count);
        viewCountRepository.save(entity);
    }
}
