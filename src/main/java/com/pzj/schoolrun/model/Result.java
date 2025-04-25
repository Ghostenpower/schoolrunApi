package com.pzj.schoolrun.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true) // 支持链式调用
public class Result<T> {
    private Integer code;  // 业务状态码（非HTTP状态码）
    private String msg;    // 提示信息
    private T data;        // 响应数据（泛型）

    //------------------ 成功响应静态方法 ------------------//
    public static <T> Result<T> success(T data) {
        return new Result<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> success(StatusCode resultCode, T data) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public static Result<Void> success() {
        return success(null);
    }

    public static Result<Map<String, Long>> successCourierId(Long courierId) {
        Map<String, Long> dataMap = new HashMap<>();
        dataMap.put("courierId", courierId);
        return new Result<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), dataMap);
    }

    //------------------ 错误响应静态方法 ------------------//
    public static Result<Void> error(String msg) {
        return new Result<>(StatusCode.FAIL.getCode(), msg, null);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static Result<?> error(StatusCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(), null);
    }

    //------------------ 链式调用增强 ------------------//
    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }
}
