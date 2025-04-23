package com.pzj.schoolrun.service;

import com.pzj.schoolrun.entity.Addresses;
import com.pzj.schoolrun.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.schoolrun.entity.Tasks;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.dto.orders.OrderDetailDTO;
import com.pzj.schoolrun.model.vo.orders.OrdersAddVO;
import com.pzj.schoolrun.model.vo.orders.OrdersCancelVO;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author
 * @since 2025-04-08
 */
public interface IOrdersService extends IService<Orders> {
    /**
     * 新增订单
     *
     * @param ordersAddVO 新增订单的请求参数
     * @return 操作结果
     */
    Result<?> addOrder(OrdersAddVO ordersAddVO);
    /**
     * 取消订单
     *
     * @param ordersCancelVO 取消订单的请求参数
     * @return 操作结果
     */
    Result<?> cancelOrder(OrdersCancelVO ordersCancelVO);
    List<Orders> getByOrderIds(Long orderIds);
    List<OrderDetailDTO> getAllOrders(Integer orderStatus);
    List<OrderDetailDTO> getAllOrders(Long userId, Integer orderStatus);

}