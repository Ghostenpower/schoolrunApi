package com.pzj.schoolrun.model.vo.feedback;

import lombok.Data;

/**
 * 反馈信息展示用对象（不包含ID和创建时间）
 */
@Data
public class FeedbackVO {
    /**
     * 反馈内容
     */
    private String suggestion;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 图片列表url(逗号分隔)
     */
    private String imagesUrl;
}
