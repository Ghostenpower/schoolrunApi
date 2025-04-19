package com.pzj.schoolrun.controller;


import com.github.pagehelper.PageInfo;
import com.pzj.schoolrun.entity.Addresses;
import com.pzj.schoolrun.model.vo.addresses.AddressesUpdateVO;
import com.pzj.schoolrun.model.vo.addresses.AddressesVO;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.service.impl.AddressesServiceImpl;
import com.pzj.schoolrun.util.page.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 地址信息表 前端控制器
 * </p>
 *
 * @author pzj
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/addresses")
@Slf4j
public class AddressesController extends BaseController {

    @Autowired
    private AddressesServiceImpl addressesService;

    @GetMapping("/getList")
    public Result<?> getAddressList() {
        startPage();
        List<Addresses> list = addressesService.getList();

        return Result.success(PageInfo.of(list));
    }

    @GetMapping("/getByUserId")
    public Result<?> getByUserId() {
        PageUtils.startPage();
        Long userId = getUserId();
        List<Addresses> list = addressesService.getByUserId(userId);
        return Result.success(PageInfo.of(list));
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody AddressesVO addressesVO) {
        Long userId = getUserId();
        Addresses address = Addresses.builder()
                .userId(userId)
                .addressType(addressesVO.getAddressType())
                .recipientName(addressesVO.getRecipientName())
                .phone(addressesVO.getPhone())
                .detailedAddress(addressesVO.getDetailedAddress()).build();
        addressesService.save(address);
        return Result.success();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody AddressesUpdateVO addressesUpdateVO) {
        Long userId = getUserId();
        if(!Objects.equals(userId, addressesUpdateVO.getUserId())){
            return Result.error("非法操作");
        }
        Addresses address = Addresses.builder()
                .addressId(addressesUpdateVO.getAddressId())
                .userId(addressesUpdateVO.getUserId())
                .recipientName(addressesUpdateVO.getRecipientName())
                .phone(addressesUpdateVO.getPhone())
                .detailedAddress(addressesUpdateVO.getDetailedAddress())
                .isDefault(addressesUpdateVO.getIsDefault()).build();
        addressesService.updateById(address);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result<?> delete(@RequestParam Long addressId) {
        Long userId = getUserId();
        Addresses address = addressesService.getById(addressId);
        if(!Objects.equals(userId, address.getUserId())){
            return Result.error("非法操作");
        }
        addressesService.delete(addressId);
        return Result.success();
    }
}
