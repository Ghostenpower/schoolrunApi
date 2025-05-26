package com.pzj.schoolrun.controller;

import com.pzj.schoolrun.entity.Users;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.StatusCode;
import com.pzj.schoolrun.model.dto.UserLoginDTO;
import com.pzj.schoolrun.model.vo.users.BalanceOperationVO;
import com.pzj.schoolrun.model.vo.users.UserLoginVO;
import com.pzj.schoolrun.model.vo.users.UserRegisterVO;
import com.pzj.schoolrun.model.vo.users.UserUpdateVO;
import com.pzj.schoolrun.service.IUsersService;
import com.pzj.schoolrun.util.JWT;
import com.pzj.schoolrun.util.JwtUtil;
import com.pzj.schoolrun.util.MD5Util;
import com.pzj.schoolrun.util.MultiRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
@Slf4j
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

    @PostMapping("updateAvatar")
    public Result<?> updateUserAvatar( String userAvatarUrl) {
        Long userId = getUserId();
        Users user = usersService.getById(userId);
        user.setAvatarUrl(userAvatarUrl);
        usersService.updateById(user);
        return Result.success();
    }

    @GetMapping("/getOneById")
    public Result<?> getOneById(@RequestParam Long userId) {
        Users user = usersService.getById(userId);
        return Result.success(user);
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
            //通过名字获取注册用户id
            Users newUser = usersService.lambdaQuery()
                    .eq(Users::getUsername, userRegisterVO.getUsername()).one();
            Long userId = newUser.getUserId();
            String username  = newUser.getUsername();
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("username", username);

            return Result.success(map);
        } catch (Exception e) {
            return Result.error("注册失败：" + e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody UserUpdateVO userUpdateVO) {
        try {
            Long userId = getUserId();

            if (!Objects.equals(userId, userUpdateVO.getUserId())) {
                return Result.error("非法操作");
            }
            Users user = new Users();
            user.setUserId(userUpdateVO.getUserId());
            if (userUpdateVO.getUsername() != null && !userUpdateVO.getUsername().isEmpty()) {
                user.setUsername(userUpdateVO.getUsername());
            }
            if (userUpdateVO.getPhone() != null && !userUpdateVO.getPhone().isEmpty()) {
                user.setPhone(userUpdateVO.getPhone());
            }
            if (userUpdateVO.getEmail() != null && !userUpdateVO.getEmail().isEmpty()) {
                user.setEmail(userUpdateVO.getEmail());
            }
            if (userUpdateVO.getPassword() != null && !userUpdateVO.getPassword().isEmpty()) {
                user.setPassword(MD5Util.encrypt(userUpdateVO.getPassword()));
            }
            if (userUpdateVO.getAvatarUrl() != null && !userUpdateVO.getAvatarUrl().isEmpty()) {
                user.setAvatarUrl(userUpdateVO.getAvatarUrl());
                log.info(user.getAvatarUrl());
            }
            user.setUpdatedAt(LocalDateTime.now());
            log.info("User ID: {}, Username: {}, Phone: {}", user.getUserId(), user.getUsername(), user.getPhone());
            usersService.updateById(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    @PostMapping("/recharge")
    public Result<?> recharge(@RequestBody BalanceOperationVO vo) {
        try {
            Long userId = getUserId();
            return usersService.rechargeBalance(userId, vo.getBalance());
        } catch (Exception e) {
            return Result.error("充值失败: " + e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public Result<?> withdraw(@RequestBody BalanceOperationVO vo) {
        try {
            Long userId = getUserId();
            return usersService.withdrawBalance(userId, vo.getBalance());
        } catch (Exception e) {
            return Result.error("提现失败: " + e.getMessage());
        }
    }

    @PostMapping("getUserByCourierId")
    public Result<?> getUserByCourierId(@RequestParam Long courierId) {
        Users user = usersService.getUserByCourierId(courierId);
        return Result.success(user);
    }

    @PostMapping("verify")
    public Result<?> verify() {
        Long userId = getUserId();
        Users user = usersService.getById(userId);
        // 4. 构建返回对象
        UserLoginDTO userLoginDTO = new UserLoginDTO();
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
    }

    @PostMapping("getCourierUserInfoByTaskId")
    public Result<?> getCourierUserInfoByTaskId(@RequestParam Long taskId) {
        Users user = usersService.getCourierUserInfoByTaskId(taskId);
        return Result.success(user);
    }
}
