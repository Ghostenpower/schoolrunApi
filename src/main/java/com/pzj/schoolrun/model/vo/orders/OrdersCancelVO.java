package com.pzj.schoolrun.model.vo.orders;

import lombok.Data;

@Data
public class OrdersCancelVO {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 取消原因
     */
    private String cancelReason;
}
