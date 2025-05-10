package com.pzj.schoolrun.service;

import com.pzj.schoolrun.entity.Couriers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 跑腿员信息表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
public interface ICouriersService extends IService<Couriers> {
    Long getCourierIdByUserId(Long userId);
}
