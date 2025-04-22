package com.pzj.schoolrun.model.vo.orders;

import lombok.Data;

@Data
public class OrdersAddVO {
    /**
     * 关联任务ID
     */
    private Long taskId;

    /**
     * 接单跑腿员ID
     */
    private Long courierId;
}
