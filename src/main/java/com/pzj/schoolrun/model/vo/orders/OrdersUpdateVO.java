package com.pzj.schoolrun.model.vo.orders;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdersUpdateVO {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单状态(1=待完成,2=已完成,3=已取消)
     */
    private Integer orderStatus;



    /**
     * 关联任务ID
     */
    private Long taskId;

    /**
     * 接单跑腿员ID
     */
    private Long courierId;

    /**
     * 订单状态(1=待完成,2=已完成,3=已取消)
     */


    /**
     * 接单时间
     */
    private LocalDateTime acceptTime;

    /**
     * 完成时间
     */
    private LocalDateTime completionTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

}
