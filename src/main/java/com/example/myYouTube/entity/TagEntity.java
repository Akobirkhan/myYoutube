package com.example.myYouTube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tag")
public class TagEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
