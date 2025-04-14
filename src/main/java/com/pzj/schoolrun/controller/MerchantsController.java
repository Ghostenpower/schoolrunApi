package com.pzj.schoolrun.controller;


import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.merchants.MerchantRegisterVO;
import com.pzj.schoolrun.model.vo.merchants.MerchantUpdateVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商家信息表 前端控制器
 * </p>
 *
 * @author ljj
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/merchants")
public class MerchantsController {
    @PostMapping("/apply")
    public Result<?> apply(@RequestBody MerchantRegisterVO merchantRegisterVO) {
        return Result.success();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody MerchantUpdateVO merchantUpdateVO) {
        return Result.success();
    }

}
