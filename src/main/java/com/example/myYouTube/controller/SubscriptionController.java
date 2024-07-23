package com.example.myYouTube.controller;


import com.example.myYouTube.dto.subscription.SubscriptionDto;
import com.example.myYouTube.enums.Status;
import com.example.myYouTube.service.SubscriptionService;
import com.example.myYouTube.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<String> createSubscription(@RequestParam String chanelId) {
        return ResponseEntity.ok(subscriptionService.create(chanelId));
    }

    @PutMapping("/update-status/{channelId}")
    public ResponseEntity<SubscriptionDto> changeStatus(@PathVariable String channelId,
                                                        @RequestParam Status status) {
        return ResponseEntity.ok(subscriptionService.updateStatus(channelId,status));
    }

    @GetMapping("/subscription-list")
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        Long userId = SecurityUtil.getProfileId();
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions(userId));
    }

    @GetMapping("/subscription-list-admin/{userId}")
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptionsForAdmin(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions(userId));
    }
}
