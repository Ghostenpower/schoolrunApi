package com.pzj.schoolrun.mapper;

import com.pzj.schoolrun.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pzj.schoolrun.model.dto.orders.OrderDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    List<OrderDetailDTO> getAllOrders(Integer orderStatus);
    List<OrderDetailDTO> getAllOrders(Long userId, Integer orderStatus);
}
