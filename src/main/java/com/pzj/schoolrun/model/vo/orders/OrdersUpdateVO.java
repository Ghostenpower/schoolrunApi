package com.pzj.schoolrun.model.vo.orders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersUpdateVO {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单状态(1=待完成,2=已完成,3=已取消)
     */
    private Integer orderStatus;

}
