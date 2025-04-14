package com.pzj.schoolrun.exception;

import com.pzj.schoolrun.model.StatusCode;
import lombok.Getter;

@Getter
public class PasswordPolicyException extends BusinessException {

    public PasswordPolicyException(StatusCode statusCode, String message) {
        super(statusCode, message);
    }

    public PasswordPolicyException(String message) {
        this(StatusCode.PASSWORD_POLICY_ERROR, message);
    }
}
