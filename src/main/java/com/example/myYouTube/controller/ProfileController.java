package com.example.myYouTube.controller;


import com.example.myYouTube.dto.profile.ProfileCreateDto;
import com.example.myYouTube.dto.profile.ProfileDto;
import com.example.myYouTube.dto.profile.ProfileUpdateDto;
import com.example.myYouTube.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PutMapping("/change/password")
    public ResponseEntity<String> changePassword(@RequestParam String oldPassword,
                                                 @RequestParam String newPassword) {
        String response = profileService.changePassword(oldPassword, newPassword);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change/email")
    public ResponseEntity<String> changeEmail(@RequestParam String newEmail) {
        String response = profileService.changeEmail(newEmail);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token) {
        String response = profileService.verifyEmail(token);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/detail")
    public ResponseEntity<ProfileDto> update(@RequestBody ProfileUpdateDto dto) {
        ProfileDto response = profileService.update(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getProfileDetail")
    public ResponseEntity<ProfileDto> getProfileDetail() {
        return ResponseEntity.ok().body(profileService.getProfileDetail());
    }
    @PostMapping("/admin/createProfile")
    public ResponseEntity<ProfileDto> createProfile(@Valid @RequestBody ProfileCreateDto profileCreateDto) {
        return ResponseEntity.ok().body(profileService.createProfile(profileCreateDto));
    }

}