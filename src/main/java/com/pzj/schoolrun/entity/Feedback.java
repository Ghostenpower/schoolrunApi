package com.pzj.schoolrun.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Feedback {
    private Long id;
    private String suggestion;
    private String contact;
    private byte[] imageData;
    private LocalDateTime createTime;
}
