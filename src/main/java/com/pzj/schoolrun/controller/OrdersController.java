package com.pzj.schoolrun.controller;


import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.orders.OrdersAddVO;
import com.pzj.schoolrun.model.vo.orders.OrdersCancelVO;
import com.pzj.schoolrun.model.vo.orders.OrdersUpdateVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class OrdersController {
    @PostMapping("/add")
    public Result<?> add(@RequestBody OrdersAddVO ordersAddVO) {
        return Result.success();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody OrdersUpdateVO ordersUpdateVO) {
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<?> cancel(@RequestBody OrdersCancelVO ordersCancelVO) {
        return Result.success();
    }
}
