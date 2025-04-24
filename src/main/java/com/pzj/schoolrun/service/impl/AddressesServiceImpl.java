package com.pzj.schoolrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pzj.schoolrun.entity.Addresses;
import com.pzj.schoolrun.mapper.AddressesMapper;
import com.pzj.schoolrun.service.IAddressesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地址信息表 服务实现类
 * </p>
 *
 * @author
 * @since 2025-04-08
 */
@Service
public class AddressesServiceImpl extends ServiceImpl<AddressesMapper, Addresses> implements IAddressesService {

    @Autowired
    private AddressesMapper addressesMapper;

    @Override
    public List<Addresses> getList() {
        LambdaQueryWrapper<Addresses> queryWrap = new LambdaQueryWrapper<>();
        queryWrap.eq(Addresses::getStatus, 1);
        return addressesMapper.selectList(queryWrap);
    }

    @Override
    public List<Addresses> getByUserId(Long userId) {
        LambdaQueryWrapper<Addresses> queryWrap = new LambdaQueryWrapper<>();
        queryWrap.eq(Addresses::getUserId, userId)
                .eq(Addresses::getStatus, 1);
        return addressesMapper.selectList(queryWrap);
    }

    @Override
    public void delete(Long addressId) {
        // 校验 addressId 是否存在
        Addresses addresses = addressesMapper.selectById(addressId);
        if (addresses == null) {
            throw new RuntimeException("地址不存在");
        }
        // 只更新 status 而非删除
        addresses.setStatus(0);
        addressesMapper.updateById(addresses);
    }
}
