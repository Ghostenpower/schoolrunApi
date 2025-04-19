package com.pzj.schoolrun.controller;

import com.github.pagehelper.PageInfo;
import com.pzj.schoolrun.entity.Orders;
import com.pzj.schoolrun.entity.Tasks;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.orders.OrdersAddVO;
import com.pzj.schoolrun.model.vo.orders.OrdersCancelVO;
import com.pzj.schoolrun.model.vo.orders.OrdersUpdateVO;
import com.pzj.schoolrun.service.IOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrdersController extends BaseController {
    @Autowired
    private IOrdersService ordersService;

    @GetMapping("/getList")
    public Result<?> list() {
        startPage();
        List<Orders> list = ordersService.getAllOrders();
        return Result.success(PageInfo.of(list));
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
                .taskId(ordersUpdateVO.getTaskId())
                .courierId(ordersUpdateVO.getCourierId())
                .orderStatus(ordersUpdateVO.getOrderStatus())
                .acceptTime(ordersUpdateVO.getAcceptTime())
                .completionTime(ordersUpdateVO.getCompletionTime())
                .cancelTime(ordersUpdateVO.getCancelTime())
                .cancelReason(ordersUpdateVO.getCancelReason())
                .createdAt(ordersUpdateVO.getCreatedAt())
                .updatedAt(ordersUpdateVO.getUpdatedAt())

                .build();

        ordersService.updateById(orders);
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<?> cancel(@RequestBody OrdersCancelVO ordersCancelVO) {
        return ordersService.cancelOrder(ordersCancelVO);    }
}
