package com.pzj.schoolrun.controller;

import com.github.pagehelper.PageInfo;
import com.pzj.schoolrun.entity.Orders;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.StatusCode;
import com.pzj.schoolrun.model.dto.orders.OrderDetailDTO;
import com.pzj.schoolrun.model.vo.orders.OrdersAddVO;
import com.pzj.schoolrun.model.vo.orders.OrdersCancelVO;
import com.pzj.schoolrun.model.vo.orders.OrdersUpdateVO;
import com.pzj.schoolrun.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lyp
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrdersController extends BaseController {
    @Autowired
    private IOrdersService ordersService;

    @GetMapping("/getList")
    public Result<?> list(@RequestParam Integer orderStatus) {
        startPage();
        Long userId = getUserId();
        List<OrderDetailDTO> list = ordersService.getAllOrders(userId,orderStatus);
        return Result.success(PageInfo.of(list));
    }

    @GetMapping("/getOneById")
    public Result<?> getOneById(@RequestParam Long orderId) {
        Orders orders = ordersService.getById(orderId);
        return Result.success(orders);
    }


    @PostMapping("/add")
    public Result<?> add(@RequestBody OrdersAddVO ordersAddVO) {
        Orders order = Orders.builder()
                .taskId(ordersAddVO.getTaskId())
                .courierId(ordersAddVO.getCourierId())
                .createdAt(LocalDateTime.now())
                .orderStatus(1) // 假设初始状态为“待完成”
                .build();

        boolean saveResult = ordersService.save(order);
        if (saveResult) {
            return Result.success();
        } else {
            return Result.error("新增订单失败");
        }
    }


    @PostMapping("/update")
    public Result<?> update(@RequestBody OrdersUpdateVO ordersUpdateVO) {
        log.info("Updating order with data: {}", ordersUpdateVO);

        Orders orders = Orders.builder()
                .orderId(ordersUpdateVO.getOrderId())
                .build();

        ordersService.updateById(orders);
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<?> cancel(@RequestBody OrdersCancelVO ordersCancelVO) {
        return ordersService.cancelOrder(ordersCancelVO);
    }

    @PostMapping("/complete")
    public Result<?> complete(@RequestBody Long orderId) {
        //检查订单是否存在
        Long userId = getUserId();
        Orders order = ordersService.getById(orderId);
        if (order == null) {
            return Result.error(StatusCode.ORDER_NOT_EXIST);
        }
        if (order.getCourierId().equals(userId)) {
            return Result.error(StatusCode.INVALID_OPERATION);
        }
        if (order.getOrderStatus() ==2) {
            return Result.error("订单已完成");
        }
        if (order.getOrderStatus() ==3) {
            return Result.error("订单已取消");
        }
        order.setOrderStatus(2);
        order.setCompletionTime(LocalDateTime.now());
        boolean result = ordersService.updateById(order);
        if (result) {
            return Result.success();
        } else {
            return Result.error(StatusCode.SERVER_ERROR);
        }
    }
}