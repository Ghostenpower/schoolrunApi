package com.pzj.schoolrun.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pzj.schoolrun.model.StatusCode;
import com.pzj.schoolrun.util.JWT;
import com.pzj.schoolrun.util.ThreadLocalUntil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.pzj.schoolrun.model.Result;

import java.util.Map;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String token = request.getHeader("token");

        try {
            // 校验token是否合法
            if (token == null) {
                // 构建统一的返回结果：Token 为空，验证失败
                Result<?> result = Result.error("NOT_LOGIN");

                // 设置响应的 Content-Type 为 JSON 格式
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // 使用 Jackson ObjectMapper 将 Result 对象转化为 JSON 字符串
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(result);

                // 写出 JSON 响应
                response.getWriter().write(jsonResponse);

                return false;
            }

            // 解析 JWT
            Map<String, Object> claims = JWT.parseJWT(token);
            log.info("JWT claims: {}", claims);

            // 提取用户名和加密后的密码
            Integer user_id = (Integer) claims.get("userId");
            Long userId = user_id.longValue();

            request.setAttribute("userId", userId);
            ThreadLocalUntil.setUserId(userId);

            // 你也可以将 claims 设置到 ThreadLocal 中以便后续使用
            ThreadLocalUntil.set("token",claims);

            return true;
        } catch (Exception e) {
            // Token 校验失败
            log.error("Token error: {}", e.getMessage());

            // 构建统一的返回结果：Token 验证失败
            Result<?> result = Result.error(StatusCode.NOT_LOGIN);

            // 设置响应的 Content-Type 为 JSON 格式
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // 使用 Jackson ObjectMapper 将 Result 对象转化为 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(result);

            // 写出 JSON 响应
            response.getWriter().write(jsonResponse);

            return false;
        }
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        ThreadLocalUntil.clear();
    }
}
