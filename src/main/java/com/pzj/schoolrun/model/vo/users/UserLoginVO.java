package com.pzj.schoolrun.model.vo.users;

import lombok.Data;

@Data
public class UserLoginVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 未加密密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
