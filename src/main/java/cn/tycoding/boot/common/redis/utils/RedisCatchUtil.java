package cn.tycoding.boot.common.redis.utils;

import java.util.UUID;

/**
 * @author tycoding
 * @since 2021/4/12
 */
public class RedisCatchUtil {

    public static String getKey() {
        return UUID.randomUUID().toString();
    }
}
