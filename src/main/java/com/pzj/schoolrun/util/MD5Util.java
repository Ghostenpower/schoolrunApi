package com.pzj.schoolrun.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final String SALT = "schoolrun"; // 加盐值，增加安全性

    public static String encrypt(String password) {
        try {
            // 加盐
            String inputWithSalt = password + SALT;
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(inputWithSalt.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密出错", e);
        }
    }

    public static boolean verify(String password, String md5) {
        return encrypt(password).equals(md5);
    }
} 