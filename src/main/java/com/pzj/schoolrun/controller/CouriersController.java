package com.pzj.schoolrun.controller;



import com.github.benmanes.caffeine.cache.Cache;
import com.pzj.schoolrun.entity.Couriers;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.couriers.CouriersVO;
import com.pzj.schoolrun.service.impl.CouriersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @Autowired
    private Cache<String, Object> caffeineCache;
    @PostMapping("/apply")
    public Result<?> apply(@RequestBody CouriersVO couriersVO) {
        Couriers couriers = Couriers.builder()
                .userId(getUserId())
                .idCard(couriersVO.getIdCard())
                .idCardFront(couriersVO.getIdCardFront())
                .idCardBack(couriersVO.getIdCardBack())
                .studentCard(couriersVO.getStudentCard())
                .creditScore(0)
                .totalOrders(0)
                .status(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Long userId = getUserId();
        if (!userId.equals(couriers.getUserId())) {
            return Result.error("用户id不能为空");
        }
        //不可再次申请
        if (couriersService.query().eq("user_id", userId).one() != null) {
            return Result.error("不可再次申请");
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

    @GetMapping("/getOneById")
    public Result<?> getOneById(@RequestParam Long courierId) {
        Couriers couriers = couriersService.getById(courierId);
        if (couriers == null) {
            return Result.error("跑腿员不存在");
        }
        return Result.success(couriers);
    }

    @GetMapping("/getCourierId")
    public Result<?> getCourierId() {
        Long userId = getUserId();
        Couriers couriers = couriersService.query().eq("user_id", userId).one();
        if (couriers == null) {
            return Result.error("跑腿员不存在");
        }
        Long courierId = couriers.getCourierId();
        return Result.success(courierId);
    }
    @PostMapping("/updateLocation")
    public Result<?> updateCourierLocation(
            @RequestParam Long courierId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        String locationKey = "courierId:" + courierId;
        String locationValue = latitude + "," + longitude;

        caffeineCache.put(locationKey, locationValue);

        return Result.success("位置更新成功");
    }

}
