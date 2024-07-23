package com.example.myYouTube.service;


import com.example.myYouTube.dto.channel.ChannelDto;
import com.example.myYouTube.dto.subscription.SubscriptionDto;
import com.example.myYouTube.entity.SubscriptionEntity;
import com.example.myYouTube.enums.Status;
import com.example.myYouTube.exp.AppBadException;
import com.example.myYouTube.mapper.SubscriptionMapper;
import com.example.myYouTube.repository.SubscriptionRepository;
import com.example.myYouTube.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public String create(String chanelId) {
        var entity = SubscriptionEntity
                .builder()
                .chanelId(chanelId)
                .profileId(SecurityUtil.getProfileId())
                .build();
        subscriptionRepository.save(entity);
        return entity.getId();
    }

    public SubscriptionDto updateStatus(String channelId, Status status) {
        SubscriptionEntity entity = getByChannelId(channelId);
        entity.setStatus(status);
        subscriptionRepository.save(entity);
        return toDto(entity);
    }

    public List<SubscriptionDto> getAllSubscriptions(Long userId) {
      return subscriptionRepository.findAllByProfileId(userId)
              .stream()
              .map(this::subscriptionInfo)
              .toList();
    }

    public SubscriptionEntity getByChannelId(String channelId) {
        Long profileId = SecurityUtil.getProfileId();
        return subscriptionRepository.findByChanelIdAndProfileId(channelId,profileId)
                .orElseThrow(()-> new AppBadException("subscription not found"));
    }

    private SubscriptionDto toDto(SubscriptionEntity entity) {
        return SubscriptionDto
                .builder()
                .id(entity.getId())
                .profileId(entity.getProfileId())
                .channelId(entity.getChanelId())
                .createdDate(entity.getCreatedDate())
                .unsubscribeDate(entity.getUnsubscribeDate())
                .subscriptionStatus(entity.getStatus())
                .build();
    }
    private SubscriptionDto subscriptionInfo(SubscriptionMapper mapper) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setId(mapper.getId());

        // create chanel
        ChannelDto chanel = new ChannelDto();
        chanel.setId(mapper.getChanelId());
        chanel.setName(mapper.getChannelName());
        chanel.setPhotoId(mapper.getChanelPhotoId());

        dto.setChannel(chanel);
        return dto;
    }


}
