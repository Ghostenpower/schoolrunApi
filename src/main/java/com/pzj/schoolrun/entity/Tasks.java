package com.pzj.schoolrun.entity;

import cn.hutool.core.util.DesensitizedUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 跑腿任务表
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tasks")
@Builder
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId(value = "task_id", type = IdType.AUTO)
    private Long taskId;

    /**
     * 发布用户ID
     */
    private Long userId;

    /**
     * 任务类型(1=代取快递,2=代购餐食,3=文件打印,4=其他)
     */
    private Integer taskType;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务详细描述
     */
    private String description;

    /**
     * 取件地点
     */
    private String pickupLocation;

    /**
     * 取件坐标
     */
    private String pickupCoordinates;

    /**
     * 送达地点
     */
    private String deliveryLocation;

    /**
     * 送达坐标
     */
    private String deliveryCoordinates;

    /**
     * 任务截止时间
     */
    private LocalDateTime deadline;

    /**
     * 任务金额（精确到分）
     */
    private BigDecimal price;

    /**
     * 状态(0=待接单,1=已接单,2=进行中,3=已完成,4=已取消)
     */
    private Integer status;

    /**
     * 支付状态(0=未支付,1=已支付,2=已退款)
     */
    private Integer paymentStatus;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 图片列表url(逗号分隔)
     */
    private String imagesUrl;
}
