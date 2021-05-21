package cn.tycoding.boot.common.redis.utils;

import java.util.UUID;

/**
 * Redis工具类
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class RedisCatchUtil {

    public static String getKey() {
        return UUID.randomUUID().toString();
    }
}
