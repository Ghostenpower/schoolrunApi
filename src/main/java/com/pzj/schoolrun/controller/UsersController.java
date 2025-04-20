package com.pzj.schoolrun.controller;

import com.pzj.schoolrun.entity.Users;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.StatusCode;
import com.pzj.schoolrun.model.dto.UserLoginDTO;
import com.pzj.schoolrun.model.vo.users.UserLoginVO;
import com.pzj.schoolrun.model.vo.users.UserRegisterVO;
import com.pzj.schoolrun.model.vo.users.UserUpdateVO;
import com.pzj.schoolrun.service.IUsersService;
import com.pzj.schoolrun.util.JWT;
import com.pzj.schoolrun.util.JwtUtil;
import com.pzj.schoolrun.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

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
public class UsersController extends BaseController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserLoginVO userLoginVO) {
        try {
            // 1. 查询用户是否存在
            Users user = usersService.lambdaQuery()
                    .eq(Users::getUsername, userLoginVO.getUsername())
                    .one();
            
            if (user == null) {
                return Result.error(StatusCode.USER_NOT_FOUND);
            }

            // 2. 验证密码
            if (!MD5Util.verify(userLoginVO.getPassword(), user.getPassword())) {
                return Result.error(StatusCode.INVALID_CREDENTIALS);
            }

            // 3. 生成token
            String token = JWT.createJwt(Map.of("userId", user.getUserId()));

            // 4. 构建返回对象
            UserLoginDTO userLoginDTO = new UserLoginDTO();
            userLoginDTO.setToken(token);
            userLoginDTO.setUserId(user.getUserId());
            userLoginDTO.setUsername(user.getUsername());
            userLoginDTO.setPhone(user.getPhone());
            userLoginDTO.setEmail(user.getEmail());
            userLoginDTO.setAvatarUrl(user.getAvatarUrl());
            userLoginDTO.setUserType(user.getUserType());
            userLoginDTO.setStatus(user.getStatus());
            userLoginDTO.setCreatedAt(user.getCreatedAt());
            userLoginDTO.setUpdatedAt(user.getUpdatedAt());

            return Result.success(userLoginDTO);
        } catch (Exception e) {
            return Result.error("登录失败：" + e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody UserRegisterVO userRegisterVO) {
        try {
            // 1. 检查用户名是否已存在
            long count = usersService.lambdaQuery()
                    .eq(Users::getUsername, userRegisterVO.getUsername())
                    .count();
            
            if (count > 0) {
                return Result.error(StatusCode.USERNAME_CONFLICT);
            }

            // 2. 创建新用户
            Users user = new Users();
            user.setUsername(userRegisterVO.getUsername());
            user.setPassword(MD5Util.encrypt(userRegisterVO.getPassword()));
            user.setUserType(1); // 默认学生用户
            user.setStatus(1);   // 默认正常状态
            user.setPhone(userRegisterVO.getPhone());
            user.setEmail(userRegisterVO.getEmail());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            
            // 3. 保存用户
            boolean success = usersService.save(user);
            if (!success) {
                return Result.error(StatusCode.SERVER_ERROR);
            }

            return Result.success();
        } catch (Exception e) {
            return Result.error("注册失败：" + e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody UserUpdateVO userUpdateVO) {
        return null;
    }
}
