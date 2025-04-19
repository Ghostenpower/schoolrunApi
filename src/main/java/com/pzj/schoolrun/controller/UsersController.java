package com.pzj.schoolrun.controller;


import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.vo.users.UserLoginVO;
import com.pzj.schoolrun.model.vo.users.UserRegisterVO;
import com.pzj.schoolrun.model.vo.users.UserUpdateVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户基本信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @PostMapping("/login")
    public Result<?> login(@RequestBody UserLoginVO userLoginVO) {
        return null;
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody UserRegisterVO userRegisterVO) {
        return null;
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody UserUpdateVO userUpdateVO) {
        return null;
    }

}
