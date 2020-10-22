package com.xlhj.baojia.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.xlhj.baojia.entity.vo.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lcj
 * @Date: 2020/10/22 9:39
 * @Description: TODO
 * @Version: 0.0.1
 */
public class JwtTokenUtils {

    @Value("${jwt.header}")
    private static String header;
    @Value(("${jwt.tokenPrefix}"))
    private static String tokenPrefix;
    @Value("${jwt.tokenSecret}")
    private static String tokenSecret;
    @Value("${jwt.tokenExpired}")
    private static Long tokenExpired;
    private static String LOGIN_USER_KEY = "login_user_key";
    private static String getTokenPrefix() {
        return tokenPrefix + " ";
    }

    private static final long MILLIS_SECOND = 1000;

    private static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private static RedisCacheUtils redisCache;

    /**
     * 生成token
     * @param loginUser
     * @return
     */
    public static String createToken(LoginUser loginUser) {
        String uuid = UUID.fastUUID().toString();
        loginUser.setToken(uuid);
        refreshToken(loginUser);
        Map<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_USER_KEY, uuid);
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
        return token;
    }

    /**
     * 从请求中获取用户名
     * @param request
     * @return
     */
    public String getUsernameFromToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StrUtil.isNotEmpty(token) && token.startsWith(getTokenPrefix())) {
            token = token.replace(getTokenPrefix(), "");
        }
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * 刷新token
     * @param loginUser
     */
    public static void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + tokenExpired * MILLIS_MINUTE);
        String userKey = LOGIN_USER_KEY + loginUser.getToken();
        redisCache.setCacheObject(userKey, loginUser, tokenExpired, TimeUnit.MINUTES);
    }

    /**
     * 获取用户身份信息
     * @param request
     * @return
     */
    public static LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StrUtil.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(LOGIN_USER_KEY);
            String userKey = LOGIN_USER_KEY + uuid;
            LoginUser loginUser = redisCache.getCacheObject(userKey);
            return loginUser;
        }
        return null;
    }

    /**
     * 获取请求的token
     * @param request
     * @return
     */
    private static String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StrUtil.isNotEmpty(token) && token.startsWith(getTokenPrefix())) {
            token = token.replace(getTokenPrefix(), "");
        }
        return token;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    private static Claims parseToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 验证用户信息
     * @param loginUser
     */
    public static void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }
}
