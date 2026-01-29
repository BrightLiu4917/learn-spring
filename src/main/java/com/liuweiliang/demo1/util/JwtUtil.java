package com.liuweiliang.demo1.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类：生成、验证、解析Token
 */
@Data
@Component
public class JwtUtil {
    // 1. 秘钥（生产环境：放入配置文件，加密存储，不要硬编码！）
    // 秘钥长度必须>=256位（32个字符以上），否则会报签名错误
    @Value("${jwt.secret.key:abcdefghijklmnopqrstuvwxyz1234567890abcdef}")
    private String secretKey;

    // 2. Token过期时间（单位：毫秒，这里设置1小时）
    @Value("${jwt.expire.time:3600000}")
    private long expireTime;

    // 生成SecretKey（JJWT要求使用SecretKey进行签名验证）
    private SecretKey getSecretKey() {
        // 用指定秘钥生成加密Key
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * 生成JWT Token
     * @param claims 要存入Token的自定义信息（比如用户ID、用户名、权限）
     * @return Token字符串
     */
    public String generateToken(Map<String, Object> claims) {
        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireDate = new Date(now.getTime() + expireTime);

        // 构建并生成Token
        return Jwts.builder()
                // 存入自定义信息（Claims）
                .setClaims(claims)
                // 签发时间
                .setIssuedAt(now)
                // 过期时间
                .setExpiration(expireDate)
                // 签名算法+秘钥（保证Token不被篡改）
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                // 压缩成字符串
                .compact();
    }

    /**
     * 验证Token合法性（是否被篡改、是否过期）
     * @param token Token字符串
     * @return 合法返回true，非法返回false
     */
    public boolean verifyToken(String token) {
        try {
            // 解析Token，若解析失败会抛出对应异常
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            // Token过期异常
            System.out.println("Token已过期：" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // 不支持的JWT格式异常
            System.out.println("不支持的Token格式：" + e.getMessage());
        } catch (MalformedJwtException e) {
            // Token格式错误（被篡改、格式非法）
            System.out.println("Token格式错误：" + e.getMessage());
        } catch (SignatureException e) {
            // 签名错误（秘钥不匹配、Token被篡改）
            System.out.println("Token签名错误：" + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Token为空或空字符串
            System.out.println("Token不能为空：" + e.getMessage());
        }
        return false;
    }

    /**
     * 解析Token，获取存入的自定义信息（Claims）
     * @param token Token字符串
     * @return 自定义信息Map
     */
    public Map<String, Object> parseToken(String token) {
        // 先验证Token合法性（可选，也可以在调用方先验证）
        if (!verifyToken(token)) {
            throw new RuntimeException("Token非法，无法解析");
        }

        // 解析Token，获取Claims
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        // 返回自定义信息（转为Map，方便调用方使用）
        return claims;
    }
}