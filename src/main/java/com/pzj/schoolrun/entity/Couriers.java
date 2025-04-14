package com.pzj.schoolrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 跑腿员信息表
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("couriers")
public class Couriers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 跑腿员ID
     */
    @TableId(value = "courier_id", type = IdType.AUTO)
    private Long courierId;

    /**
     * 关联用户ID
     */
    private Long userId;

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

    /**
     * 信用分(0-100)
     */
    private Integer creditScore;

    /**
     * 完成订单总数
     */
    private Integer totalOrders;

    /**
     * 状态(0待审核,1已通过,2已拒绝)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
