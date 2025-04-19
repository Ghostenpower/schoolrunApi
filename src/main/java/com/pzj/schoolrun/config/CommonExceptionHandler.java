package com.pzj.schoolrun.config;

import com.pzj.schoolrun.exception.BusinessException;
import com.pzj.schoolrun.exception.PasswordPolicyException;
import com.pzj.schoolrun.model.Result;
import com.pzj.schoolrun.model.StatusCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse("")
                ));
        return Result.error(StatusCode.PARAM_ERROR.getCode(), errors.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
        return Result.error(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException: ", ex);
        return Result.error("Illegal parameters: " + ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleRuntimeException(RuntimeException ex) {
        log.error("RuntimeException: ", ex);
        return Result.error(StatusCode.FAIL.getCode(),ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleSQLException(SQLException ex) {
        log.error("捕获到 SQLException", ex);
        return Result.error(StatusCode.DATABASE_ERROR.getCode(),ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException ex) {
        log.info("业务异常 [{}] {}", ex.getStatusCode(), ex.getMessage());
        return Result.error(ex.getStatusCode().getCode(), ex.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public Result<?> handleDataAccessException(DataAccessException ex) {
        log.error("数据库操作异常", ex);
        return Result.error(StatusCode.DATABASE_ERROR.getCode(), ex.getMessage());
    }

    @ExceptionHandler(PasswordPolicyException.class)
    public Result<?> handlePasswordPolicyException(PasswordPolicyException ex) {
        log.info("密码不符合策略 [{}] {}", ex.getStatusCode(), ex.getMessage());
        return Result.error(ex.getStatusCode().getCode(), ex.getMessage());
    }
}
