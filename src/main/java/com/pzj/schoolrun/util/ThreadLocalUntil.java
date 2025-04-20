package com.pzj.schoolrun.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 线程安全的 ThreadLocal 工具类（支持泛型存储和类型安全获取）
 */
public final class ThreadLocalUntil {
    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(HashMap::new);

    // ================ 通用方法优化 ================
    /**
     * 存储键值对到当前线程（类型安全版）
     * @param key 键（建议用常量或枚举）
     * @param value 值（非null）
     * @throws IllegalArgumentException 如果value为null
     */
    public static <T> void set(String key, T value) {
        Objects.requireNonNull(value, "ThreadLocal value cannot be null");
        CONTEXT.get().put(key, value);
    }

    /**
     * 从当前线程获取值（类型安全版）
     * @param key 键
     * @param clazz 预期类型Class对象
     * @return 存储的值（可能为null）
     * @throws ClassCastException 如果类型不匹配
     */
    public static <T> T get(String key, Class<T> clazz) {
        Object value = CONTEXT.get().get(key);
        return clazz.cast(value); // 类型检查
    }

    /**
     * 获取值或默认值（安全版）
     * @param key 键
     * @param clazz 预期类型
     * @param defaultValue 默认值
     */
    public static <T> T getOrDefault(String key, Class<T> clazz, T defaultValue) {
        T value = get(key, clazz);
        return value != null ? value : defaultValue;
    }

    // ================ 专用方法 ================
    // 用户ID
    private static final String KEY_USER_ID = "userId";
    public static void setUserId(Long userId) {
        set(KEY_USER_ID, userId);
    }
    public static Long getUserId() {
        return get(KEY_USER_ID, Long.class);
    }

//    // 分页参数
//    private static final String KEY_PAGE_NUM = "pageNum";
//    private static final String KEY_PAGE_SIZE = "pageSize";
//    public static void setPageNum(Integer pageNum) {
//        set(KEY_PAGE_NUM, pageNum);
//    }
//    public static Integer getPageNum() {
//        return get(KEY_PAGE_NUM, Integer.class);
//    }
//    public static void setPageSize(Integer pageSize) {
//        set(KEY_PAGE_SIZE, pageSize);
//    }
//    public static Integer getPageSize() {
//        return get(KEY_PAGE_SIZE, Integer.class);
//    }

    // ================ 维护方法 ================
    /**
     * 清除当前线程的所有存储
     */
    public static void clear() {
        CONTEXT.remove();
    }

    /**
     * 删除指定键的值
     */
    public static void remove(String key) {
        CONTEXT.get().remove(key);
    }

    private ThreadLocalUntil() {
        throw new AssertionError("No instances allowed");
    }
}