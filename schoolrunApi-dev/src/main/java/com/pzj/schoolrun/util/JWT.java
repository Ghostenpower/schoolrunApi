package com.pzj.schoolrun.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JWT {
    private static final String signKey = "ghostensuger123456789012345wfnhvbieribniernbijenbiwg"; // 32字节
    private static final byte[] keyBytes = Base64.getEncoder().encode(signKey.getBytes());
    private static final SecretKeySpec secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    public static String createJwt(Map<String, Object> claims) {
        long expire = 4320000L;
        return Jwts.builder()
                .addClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static Integer getUserId(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.get("user_id", Integer.class);
    }
}
