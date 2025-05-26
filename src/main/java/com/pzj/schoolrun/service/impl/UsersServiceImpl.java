package com.pzj.schoolrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pzj.schoolrun.entity.Users;
import com.pzj.schoolrun.mapper.UsersMapper;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.StatusCode;
import com.pzj.schoolrun.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 用户基本信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
        @Autowired
        private UsersMapper usersMapper;

        @Override
        @Transactional
        public Result<?> rechargeBalance(Long userId, BigDecimal balance) {
            Users user = getById(userId);
            if (user == null) return Result.error(StatusCode.USER_NOT_FOUND);
            if (balance.compareTo(BigDecimal.ZERO) <= 0) return Result.error("金额必须大于0");

            // 确保余额字段不为null
            if (user.getBalance() == null) {
                user.setBalance(BigDecimal.ZERO);
            }

            user.setBalance(user.getBalance().add(balance));
            user.setUpdatedAt(LocalDateTime.now());
            updateById(user);
            return Result.success(Map.of("newBalance", user.getBalance()));
        }

        @Override
        @Transactional
        public Result<?> withdrawBalance(Long userId, BigDecimal balance) {
            Users user = getById(userId);
            if (user == null) return Result.error(StatusCode.USER_NOT_FOUND);
            if (balance.compareTo(BigDecimal.ZERO) <= 0) return Result.error("金额必须大于0");
            if (user.getBalance().compareTo(balance) < 0) return Result.error("余额不足");

            user.setBalance(user.getBalance().subtract(balance));
            user.setUpdatedAt(LocalDateTime.now());
            updateById(user);
            return Result.success(Map.of("newBalance", user.getBalance()));
        }

    @Override
    @Transactional
    public Result<?> payCommission(Long userId, BigDecimal amount) {
        Users user = getById(userId);
        if (user == null) return Result.error(StatusCode.USER_NOT_FOUND);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) return Result.error("金额必须大于0");
        if (user.getBalance().compareTo(amount) < 0) return Result.error("余额不足，请先充值！");

        user.setBalance(user.getBalance().subtract(amount));
        user.setUpdatedAt(LocalDateTime.now());
        updateById(user);
        return Result.success(Map.of("newBalance", user.getBalance()));
    }

    @Override
    @Transactional
    public Result<?> commissionReceived(Long userId, BigDecimal amount) {
        Users user = getById(userId);
        if (user == null) return Result.error(StatusCode.USER_NOT_FOUND);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) return Result.error("金额必须大于0");

        // 确保余额字段不为null
        if (user.getBalance() == null) {
            user.setBalance(BigDecimal.ZERO);
        }

        user.setBalance(user.getBalance().add(amount));
        user.setUpdatedAt(LocalDateTime.now());
        updateById(user);
        return Result.success(Map.of("newBalance", user.getBalance()));
    }

    @Override
    public Users getCourierUserInfoByTaskId(Long taskId) {
        return usersMapper.getCourierUserInfoByTaskId(taskId);
    }


    @Override
    public Users getUserByCourierId(Long courierId) {
        return usersMapper.getUserByCourierId(courierId);
    }
}


