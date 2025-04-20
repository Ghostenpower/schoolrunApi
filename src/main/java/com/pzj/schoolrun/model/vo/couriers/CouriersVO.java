package com.pzj.schoolrun.model.vo.couriers;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouriersVO {
    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 身份证正面照片URL
     */
    private String idCardFront;

    /**
     * 身份证反面照片URL
     */
    private String idCardBack;

    /**
     * 学生证照片URL
     */
    private String studentCard;
}
