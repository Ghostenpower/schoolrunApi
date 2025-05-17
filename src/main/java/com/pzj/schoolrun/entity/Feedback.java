package com.pzj.schoolrun.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Feedback {
    private Long id;
    private String suggestion;
    private String contact;
    /**
     * 图片列表url(逗号分隔)
     */
    private String imagesUrl;
    private LocalDateTime createTime;
}
