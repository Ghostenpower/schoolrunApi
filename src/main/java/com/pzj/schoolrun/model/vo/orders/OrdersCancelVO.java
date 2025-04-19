package com.pzj.schoolrun.model.vo.orders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "OrdersCancelVO", description = "取消订单请求参数")
public class OrdersCancelVO {
    /**
     * 订单ID
     */

    @ApiModelProperty(value = "订单 ID", required = true)
    private Long orderId;

    /**
     * 取消原因
     */
    @ApiModelProperty(value = "取消原因")
    private String cancelReason;
}
