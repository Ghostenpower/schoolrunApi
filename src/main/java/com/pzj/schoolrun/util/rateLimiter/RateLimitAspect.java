package com.pzj.schoolrun.util.rateLimiter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import com.pzj.schoolrun.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class RateLimitAspect {


    // 缓存 RateLimiter 实例
    private final Cache<String, RateLimiter> rateLimiterCache = CacheBuilder.newBuilder()
            .maximumSize(100)  // 限制缓存大小
            .build();

    // 定义切点，匹配所有标注了 @Limit 注解的方法
    @Pointcut("@annotation(limit)")
    public void limitPointcut(Limit limit) {}

    // 环绕通知，进行流量控制
    @Around("limitPointcut(limit)")
    public Object around(ProceedingJoinPoint joinPoint, Limit limit) throws Throwable {
        String key = limit.key();
        Double permitsPerSecond = limit.permitsPerSecond();
        long timeout = limit.timeout();
        TimeUnit timeUnit = limit.timeunit();
        String msg = limit.msg();

        // 从缓存中获取 RateLimiter 实例
        RateLimiter rateLimiter = rateLimiterCache.getIfPresent(key);

        // 如果缓存中没有 RateLimiter 实例，则创建一个
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(permitsPerSecond);  // 每秒允许的请求数
            rateLimiterCache.put(key, rateLimiter);
        }

        // 尝试获取令牌
        boolean acquired = rateLimiter.tryAcquire(timeout, timeUnit);
        if (!acquired) {
            log.warn("Rate limit exceeded: {}", key);
            // 超过限流，返回自定义响应类
            return Result.error(msg);
        }

        // 如果请求未超限，则继续执行方法
        return joinPoint.proceed();
    }
}
