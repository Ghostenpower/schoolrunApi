package com.pzj.schoolrun.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserLoginDTO {
    private String token;
    private Long userId;
    private String username;
    private String phone;
    private String email;
    private String avatarUrl;
    private Integer userType;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 