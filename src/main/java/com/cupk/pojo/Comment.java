package com.cupk.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private Integer circleId;
    private Integer userId;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private User user;

    // Getters and setters are provided by Lombok's @Data annotation
}