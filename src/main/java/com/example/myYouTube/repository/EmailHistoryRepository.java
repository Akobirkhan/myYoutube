package com.example.myYouTube.repository;


import com.example.myYouTube.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity, Long> {
    Optional<EmailHistoryEntity> findTop1ByEmailOrderByCreatedDateDesc(String email);

    Page<EmailHistoryEntity> findAllByEmail(String email, Pageable pageable);

    Page<EmailHistoryEntity> findAll(Pageable pageable);
}
