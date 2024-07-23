package com.example.myYouTube.repository;

import com.example.myYouTube.entity.ViewCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewCountRepository extends JpaRepository<ViewCountEntity, Integer> {
}
