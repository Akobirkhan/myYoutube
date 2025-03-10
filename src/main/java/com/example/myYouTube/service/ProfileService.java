package com.example.myYouTube.service;


import com.example.myYouTube.dto.JwtDto;
import com.example.myYouTube.dto.profile.ProfileCreateDto;
import com.example.myYouTube.dto.profile.ProfileDto;
import com.example.myYouTube.dto.profile.ProfileUpdateDto;
import com.example.myYouTube.entity.ProfileEntity;
import com.example.myYouTube.enums.ProfileStatus;
import com.example.myYouTube.exp.AppBadException;
import com.example.myYouTube.repository.ProfileRepository;
import com.example.myYouTube.util.JwtUtil;
import com.example.myYouTube.util.MD5Util;
import com.example.myYouTube.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final EmailSenderService emailSenderService;
    private final EmailHistoryService emailHistoryService;

    public String changePassword(String oldPassword, String newPassword) {
        ProfileEntity profile = SecurityUtil.getProfile();
        if (!profile.getPassword().equals(MD5Util.getMd5(oldPassword))) {
            throw new AppBadException("wrong password");
        }
        profile.setPassword(MD5Util.getMd5(newPassword));
        profileRepository.save(profile);
        return "password changed";
    }

    public String changeEmail(String newEmail) {
        ProfileEntity profile = SecurityUtil.getProfile();

        if (profile.getStatus().equals(ProfileStatus.BLOCK)) {
            throw new AppBadException("profile status is block");
        }
        if (profile.getEmail().equals(newEmail) || profileRepository.existsByEmail(newEmail)) {
            throw new AppBadException("email already in use");
        }

        String token = JwtUtil.generateToken(profile.getId(), newEmail, profile.getRole());
        emailSenderService.sendEmailForChange(token);
        return "please verify your new email.";
    }

    public String verifyEmail(String token) {
        JwtDto dto = JwtUtil.decode(token);
        String email = dto.getUsername();
        Integer profileId = dto.getId();

        emailHistoryService.isNotExpiredEmail(email);

        profileRepository.updateEmail(profileId, email);
        return "email changed successfully";
    }

    public ProfileDto update(ProfileUpdateDto dto) {
        ProfileEntity profile = SecurityUtil.getProfile();

        if (profile.getStatus().equals(ProfileStatus.BLOCK)) {
            throw new AppBadException("profile status is block");
        }

        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        ProfileEntity saved = profileRepository.save(profile);
        return toDto(saved);
    }

    private ProfileDto toDto(ProfileEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public ProfileEntity getProfile(String email) {
        return profileRepository.findByEmailAndVisibleTrue(email)
                .orElseThrow(() -> new AppBadException("profile not found"));
    }

    public ProfileDto getProfileDetail() {
        ProfileEntity profile = SecurityUtil.getProfile();
        return toDto(profile);
    }

    public ProfileDto createProfile(ProfileCreateDto dto) {
        profileRepository.findByEmailAndVisibleTrue(dto.getEmail())
                .ifPresent(profile -> {
                    throw new AppBadException("profile already exists");
                });
        ProfileEntity profile = new ProfileEntity();

        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setRole(dto.getRole());
        profile.setPassword("12345");
        profile.setSurname(dto.getSurname());
        profileRepository.save(profile);
        return toDto(profile);

    }
}
