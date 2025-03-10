package com.example.myYouTube.repository;


import com.example.myYouTube.entity.ProfileEntity;
import com.example.myYouTube.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set status =?2 where id =?1")
    void updateStatus(Long profileId, ProfileStatus status);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set email =?2 where id =?1")
    void updateEmail(Integer profileId, String email);

    boolean existsByEmail(String email);


}
