package com.pzj.schoolrun.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class ServletUtils {

    /**
     * 获取当前线程绑定的HttpServletRequest对象
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 根据参数名获取字符串类型的参数值
     */
    public static String getParameter(String name) {
        HttpServletRequest request = getRequest();
        return request.getParameter(name);
    }

    /**
     * 根据参数名获取整数类型的参数值，若参数为空则返回null
     */
    public static Integer getParameterToInt(String name) {
        String value = getParameter(name);
        return value != null ? Integer.parseInt(value) : null;
    }

    /**
     * 根据参数名获取整数类型的参数值，若参数为空或解析失败则返回默认值
     */
    public static Integer getParameterToInt(String paramName, int defaultValue) {
        String paramValue = getParameter(paramName);
        if (paramValue == null || paramValue.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue; // 如果解析失败，返回默认值
        }
    }

    public static Long getParameterToLong(String userId) {
        String value = getParameter(userId);
        return value != null ? Long.parseLong(value) : null;
    }
}
