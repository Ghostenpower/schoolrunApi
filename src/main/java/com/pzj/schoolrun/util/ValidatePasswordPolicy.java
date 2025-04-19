package com.pzj.schoolrun.util;



import com.pzj.schoolrun.exception.PasswordPolicyException;

import java.util.Arrays;
import java.util.List;

public class ValidatePasswordPolicy {

    public static void validatePasswordPolicy(String password) {
        // 基础长度校验
        if (password == null || password.length() < 8) {
            throw new PasswordPolicyException("密码长度至少需要8个字符");
        }

        // 复杂度校验规则
        checkCharacterTypes(password);

        // 高级安全规则
        checkSequentialCharacters(password);
        checkRepeatedCharacters(password);
        checkCommonPatterns(password);
    }

    /**
     * 校验必须包含的字符类型
     */
    private static void checkCharacterTypes(String password) {
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        if (!hasUpper) {
            throw new PasswordPolicyException("密码必须包含至少一个大写字母");
        }

        if (!hasLower) {
            throw new PasswordPolicyException("密码必须包含至少一个小写字母");
        }

        if (!hasDigit) {
            throw new PasswordPolicyException("密码必须包含至少一个数字");
        }

        if (!hasSpecial) {
            throw new PasswordPolicyException("密码必须包含至少一个特殊字符 (!@#$%^&*等)");
        }
    }

    /**
     * 禁止连续3个以上递增/递减字符
     */
    private static void checkSequentialCharacters(String password) {
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length - 2; i++) {
            if (isSequential(chars[i], chars[i + 1], chars[i + 2])) {
                throw new PasswordPolicyException("密码不能包含连续三个递增或递减字符");
            }
        }
    }

    private static boolean isSequential(char a, char b, char c) {
        return (a + 1 == b && b + 1 == c) ||
                (a - 1 == b && b - 1 == c);
    }

    /**
     * 禁止4个以上重复字符
     */
    private static void checkRepeatedCharacters(String password) {
        if (password.matches(".*(.)\\1{3,}.*")) {
            throw new PasswordPolicyException("密码不能包含四个以上重复字符");
        }
    }

    /**
     * 禁止常见弱密码模式
     */
    private static void checkCommonPatterns(String password) {
        List<String> forbiddenPatterns = Arrays.asList(
                "123456", "password", "qwerty",
                "admin", "welcome", "abc123"
        );

        String lowerPassword = password.toLowerCase();
        for (String pattern : forbiddenPatterns) {
            if (lowerPassword.contains(pattern)) {
                throw new PasswordPolicyException("密码不能包含常见弱密码组合");
            }
        }
    }
}



