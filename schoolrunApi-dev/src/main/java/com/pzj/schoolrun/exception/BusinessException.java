package com.pzj.schoolrun.exception;

import com.pzj.schoolrun.model.StatusCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final StatusCode statusCode;

    public BusinessException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.statusCode = statusCode;
    }

    public BusinessException(StatusCode statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
