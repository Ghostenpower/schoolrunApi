package com.pzj.schoolrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pzj.schoolrun.entity.Users;
import com.pzj.schoolrun.model.Result;

import java.math.BigDecimal;

/**
 * <p>
 * 用户基本信息表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
public interface IUsersService extends IService<Users> {
    Result<?> rechargeBalance(Long userId, BigDecimal balance);
    Result<?> withdrawBalance(Long userId, BigDecimal balance);

    Users getUserByCourierId(Long courierId);
    Result<?> payCommission(Long userId, BigDecimal amount);
    Result<?> commissionReceived(Long userId, BigDecimal amount);
}
