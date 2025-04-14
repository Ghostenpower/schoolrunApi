package com.pzj.schoolrun.service.impl;

import com.pzj.schoolrun.entity.Orders;
import com.pzj.schoolrun.mapper.OrdersMapper;
import com.pzj.schoolrun.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
