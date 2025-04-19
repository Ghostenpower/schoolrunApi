package com.pzj.schoolrun.util;

/**
 * ThreadLocal工具类，用于线程间数据隔离
 */
public class ThreadLocalUntil {
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取ThreadLocal中存储的值
     * @param <T> 返回值类型
     * @return ThreadLocal中存储的值
     */
    @SuppressWarnings("unchecked")
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    /**
     * 设置值到ThreadLocal
     * @param value 要存储的值
     */
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    /**
     * 清除ThreadLocal中的值
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }

    private ThreadLocalUntil() {
        // 工具类私有构造方法
    }
}
