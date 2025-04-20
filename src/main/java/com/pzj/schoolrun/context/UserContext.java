package com.pzj.schoolrun.context;

public class UserContext {
    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        USER_ID.set(userId);
    }

    public static Long getUserId() {
        return USER_ID.get(); // 可能返回 null，需处理
    }

    public static void clear() {
        USER_ID.remove();
    }
}
