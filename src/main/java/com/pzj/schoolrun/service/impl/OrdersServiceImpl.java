package com.pzj.schoolrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pzj.schoolrun.entity.Orders;
import com.pzj.schoolrun.mapper.OrdersMapper;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.dto.orders.OrderDetailDTO;
import com.pzj.schoolrun.model.vo.orders.OrdersAddVO;
import com.pzj.schoolrun.model.vo.orders.OrdersCancelVO;
import com.pzj.schoolrun.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author
 * @since 2025-04-08
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> getByOrderIds(Long orderIds) {
        LambdaQueryWrapper<Orders> queryWrap = new LambdaQueryWrapper<>();
        queryWrap.in(Orders::getOrderId, orderIds); // 使用 in 方法查询多个 orderId
        return ordersMapper.selectList(queryWrap);
    }
    @Override
    public List<OrderDetailDTO> getAllOrders(Long userId, Integer orderStatus) {
        return ordersMapper.getAllOrders(userId, orderStatus);
    }
    @Override
    public Result<?> cancelOrder(OrdersCancelVO ordersCancelVO) {
        // 获取订单 ID
        Long orderId = ordersCancelVO.getOrderId();

        // 根据订单 ID 查询订单信息
        Orders order = ordersMapper.selectById(orderId);
        if (order == null) {
            return Result.error("订单不存在");
        }

        // 验证订单状态是否为“待完成”
        if (order.getOrderStatus() != 1) {
            return Result.error("该订单无法取消");
        }

        // 更新订单状态为“已取消”
        order.setOrderStatus(3);

        // 设置取消时间为当前时间
        order.setCancelTime(LocalDateTime.now());

        // 如果提供了取消原因，则更新取消原因
        String cancelReason = ordersCancelVO.getCancelReason();
        if (cancelReason != null && !cancelReason.isEmpty()) {
            order.setCancelReason(cancelReason);
        }

        // 更新订单信息
        int rowsAffected = ordersMapper.updateById(order);
        if (rowsAffected > 0) {
            return Result.success();
        } else {
            return Result.error("取消订单失败");
        }
    }
    @Override
    public Result<?> addOrder(OrdersAddVO ordersAddVO) {
        Orders order = Orders.builder()
                .taskId(ordersAddVO.getTaskId())
                .courierId(ordersAddVO.getCourierId())
                .createdAt(LocalDateTime.now())
                .orderStatus(1) // 假设初始状态为“待完成”
                .build();

        boolean saveResult = this.save(order);
        if (saveResult) {
            return Result.success();
        } else {
            return Result.error("新增订单失败");
        }
    }

}