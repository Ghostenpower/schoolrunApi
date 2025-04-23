package com.pzj.schoolrun.service;

import com.pzj.schoolrun.entity.Addresses;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地址信息表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
public interface IAddressesService extends IService<Addresses> {

    List<Addresses> getList();

    List<Addresses> getByUserId(Long userId);

    void delete(Long addressId);
}
