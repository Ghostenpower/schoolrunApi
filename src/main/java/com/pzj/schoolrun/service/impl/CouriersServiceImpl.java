package com.pzj.schoolrun.service.impl;

import com.pzj.schoolrun.entity.Couriers;
import com.pzj.schoolrun.mapper.CouriersMapper;
import com.pzj.schoolrun.service.ICouriersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 跑腿员信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Service
public class CouriersServiceImpl extends ServiceImpl<CouriersMapper, Couriers> implements ICouriersService {
    @Autowired
    private CouriersMapper couriersMapper;

    @Override
    public Long getCourierIdByUserId(Long userId) {
        return couriersMapper.selectCourierIdByUserId(userId);
    }
}
