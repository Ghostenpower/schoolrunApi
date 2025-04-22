package com.pzj.schoolrun.model.dto.orders;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单详细信息映射类
 */
@Data
public class OrderDetailDTO {
    // Getters and Setters
    // 订单基本信息
    private Long orderId;
    private Integer orderStatus;
    private LocalDateTime acceptTime;
    private LocalDateTime completionTime;

    // 任务信息
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private String pickupLocation;
    private String deliveryLocation;
    private LocalDateTime deadline;
    private Integer taskStatus;

    // 客户信息
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String customerAvatarUrl;

    // 快递员信息
    private Long courierId;
    private String courierName;
    private String courierPhone;
    private String courierAvatarUrl;

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderId=" + orderId +
                ", orderStatus=" + orderStatus +
                ", acceptTime=" + acceptTime +
                ", completionTime=" + completionTime +
                ", taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", deliveryLocation='" + deliveryLocation + '\'' +
                ", deadline=" + deadline +
                ", taskStatus=" + taskStatus +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAvatarUrl='" + customerAvatarUrl + '\'' +
                ", courierId=" + courierId +
                ", courierName='" + courierName + '\'' +
                ", courierPhone='" + courierPhone + '\'' +
                ", courierAvatarUrl='" + courierAvatarUrl + '\'' +
                '}';
    }
}
