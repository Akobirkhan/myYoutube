package com.example.myYouTube.repository;


import com.example.myYouTube.entity.VideoTagEntity;
import com.example.myYouTube.mapper.VideoTagMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoTagRepository extends CrudRepository<VideoTagEntity, String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM VideoTagEntity AS v WHERE v.videoId = ?1 AND v.tagId = ?2")
    int deleteByVideoIdAndTagId(String videoId, String tagId);

    @Query(" SELECT v.id AS id, v.videoId AS videoId, t.id AS tagId, t.name AS tagName, v.createdDate AS createdDate " +
            " FROM VideoTagEntity  AS v " +
            " INNER JOIN v.tag AS t " +
            " WHERE v.videoId = ?1 ")
    List<VideoTagMapper> findAllByVideoId(String videoId);
}
