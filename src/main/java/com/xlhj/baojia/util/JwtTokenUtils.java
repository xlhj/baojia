package com.xlhj.baojia.util;

import cn.hutool.core.lang.UUID;
import com.xlhj.baojia.entity.vo.LoginUser;
import io.jsonwebtoken.*;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName TokenUtils
 * @Description token相关工具类
 * @Author liucaijing
 * @Date 2020/10/20 23:57
 * @Version 1.0
 */
public class JwtTokenUtils {

    @Value("${jwt.header}")
    private static String header;
    @Value("${jwt.tokenPrefix}")
    private static String tokenPrefix;
    @Value("${jwt.tokenSecret}")
    private static String tokenSecret;
    @Value("${jwt.tokenExpired}")
    private static Long tokenExpired;
    /**返回token前缀*/
    public String getTokenPrefix() {
        return tokenPrefix + " ";
    }
    private String AUTHORITIES_KEY = "auth";
    private SecretKey secretKey;

    /**
     * 生成token
     * @param claims
     * @return
     */
    public static String createToken(Map<String, Object> claims) {

        String token = Jwts.builder().setId(UUID.fastUUID().toString())
                .setSubject(claims.get("username").toString())
                .setIssuedAt(new Date())
                .setIssuer("admin")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpired))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
        return token;
    }
    /*public String createToken(Map<String, Object> claims) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        String token = Jwts.builder().claim(AUTHORITIES_KEY, claims)
                .setId(UUID.fastUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + tokenExpired))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(secretKey, SignatureAlgorithm.HS512).compact();
        return token;
    }*/

    /**
     * 从token中获取用户信息
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        HashMap map = (HashMap) claims.get("auth");
        LoginUser loginUser = new LoginUser(map.get("user").toString(), map.get("password").toString(), authorities);
        return new UsernamePasswordAuthenticationToken(loginUser, token, authorities);
    }
}
