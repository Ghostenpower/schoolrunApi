package com.pzj.schoolrun.model;

import lombok.Getter;

@Getter
public enum StatusCode {
    SUCCESS(1, "成功"),
    FAIL(0, "失败"),
    NOT_LOGIN(401, "未登录"),
    INVALID_CREDENTIALS(1000, "用户名或密码错误"),
    USERNAME_CONFLICT(1001, "用户名已存在"),
    USER_NOT_FOUND(1002, "用户不存在"),
    INVALID_TOKEN(1003, "无效的token"),
    DATABASE_ERROR(2001, "数据库操作失败"),
    SYSTEM_ERROR(9999, "系统繁忙"),
    PASSWORD_POLICY_ERROR(4002, "密码不符合策略"),
    PARAM_ERROR(4001, "参数错误"),
    REGISTRATION_CLOSED(4003, "系统暂未开放注册"),
    TASK_NOT_EXIST(4004, "任务不存在"),
    TASK_STATUS_ILLEGAL(4005, "任务状态非法"),
    INVALID_OPERATION(4006, "非法操作"),
    REPEATED_ACCEPT(4007, "不能重复接取任务"),
    ORDER_NOT_EXIST(4008, "订单不存在" ),
    ORDER_STATUS_ILLEGAL(4009, "订单状态非法"),
    INVALID_INPUT(4000, "非法输入"),
    INVALID_ORDER_STATUS(4010, "非法订单状态"),
    PHONE_CONFLICT(1004, "手机号已存在" ),
    USER_NOT_EXIST(1005, "用户不存在"),
    SERVER_ERROR(5000, "服务器内部错误" );
    // 提供 getter 方法
    private final int code;
    private final String message;

    // 添加带参数的构造方法
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }



    public String getMsg() {
        return message;
    }

    public Integer getCode() {return code;}
}
