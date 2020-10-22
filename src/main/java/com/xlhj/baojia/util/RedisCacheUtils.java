package com.xlhj.baojia.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lcj
 * @Date: 2020/10/22 15:52
 * @Description: Redis操作缓存类
 * @Version: 0.0.1
 */
@Component
public class RedisCacheUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存缓存信息
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存缓存信息
     * @param key
     * @param value
     * @param times 过期时间
     * @param timeUnit
     * @param <T>
     */
    public <T> void setCacheObject(String key, T value, Long times, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, times, timeUnit);
    }

    /**
     * 获取缓存信息
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
}
