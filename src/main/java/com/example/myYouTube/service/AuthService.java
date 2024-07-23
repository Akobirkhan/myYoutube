package com.example.myYouTube.service;


import com.example.myYouTube.dto.JwtDto;
import com.example.myYouTube.dto.profile.ProfileDto;
import com.example.myYouTube.dto.profile.ProfileLoginDto;
import com.example.myYouTube.dto.profile.ProfileRegistrationDto;
import com.example.myYouTube.entity.ProfileEntity;
import com.example.myYouTube.enums.ProfileRole;
import com.example.myYouTube.enums.ProfileStatus;
import com.example.myYouTube.exp.AppBadException;
import com.example.myYouTube.repository.ProfileRepository;
import com.example.myYouTube.util.JwtUtil;
import com.example.myYouTube.util.MD5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfileRepository profileRepository;
    private final EmailSenderService emailSenderService;
    private final EmailHistoryService emailHistoryService;

    public String registration(ProfileRegistrationDto dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isPresent()) {
            throw new AppBadException("Email already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.BLOCK);
        ProfileEntity saved = profileRepository.save(entity);

        String token = JwtUtil.generateToken(saved.getId(), saved.getEmail(), saved.getRole());
        emailSenderService.sendEmail(token);
        return "To complete your registration please verify your email.";
    }

    public String verification(String token) {
        JwtDto dto = JwtUtil.decode(token);
        ProfileEntity profile = profileRepository.findById(dto.getId())
                .orElseThrow(() -> new AppBadException("User not found"));

        emailHistoryService.isNotExpiredEmail(profile.getEmail());

        if (!profile.getVisible() || !profile.getStatus().equals(ProfileStatus.BLOCK)) {
            throw new AppBadException("Registration not completed");
        }

        profileRepository.updateStatus(profile.getId(), ProfileStatus.ACTIVE);
        return "registration finished successfully ";
    }

    public ProfileDto login(ProfileLoginDto dto) {
        ProfileEntity profile = profileRepository.findByEmailAndVisibleTrue(dto.getUsername())
                .orElseThrow(() -> new AppBadException("User not found"));

        if (!profile.getPassword().equals(MD5Util.getMd5(dto.getPassword()))) {
            throw new AppBadException("Wrong password");
        }

        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("status not active");
        }
        ProfileDto response = new ProfileDto();
        response.setId(profile.getId());
        response.setName(profile.getName());
        response.setSurname(profile.getSurname());
        response.setEmail(profile.getEmail());
        response.setRole(profile.getRole());
        response.setCreatedDate(profile.getCreatedDate());
        response.setJwt(JwtUtil.generateToken(profile.getId(),profile.getEmail(), profile.getRole()));
        return response;
    }
}
