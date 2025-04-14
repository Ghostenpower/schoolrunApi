package com.pzj.schoolrun.controller;


import com.pzj.schoolrun.entity.Couriers;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.service.impl.CouriersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 跑腿员信息表 前端控制器
 * </p>
 *
 * @author pzj
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/couriers")
public class CouriersController extends BaseController{

    @Autowired
    private CouriersServiceImpl couriersService;

    @PostMapping("/apply")
    public Result<?> apply(@RequestBody Couriers couriers) {
        Long userId = getUserId();
        if (!userId.equals(couriers.getUserId())) {
            return Result.error("用户id不能为空");
        }
        couriers.setStatus(0);
        couriers.setCourierId(null);
        couriers.setCreatedAt(null);
        couriers.setUpdatedAt(null);
        couriers.setTotalOrders(0);
        couriers.setCreditScore(0);
        if (couriersService.save(couriers)) {
            return Result.success("申请成功");
        }
        return Result.error("申请失败");
    }
}
