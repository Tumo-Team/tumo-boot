package cn.tycoding.boot.common.redis.config;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 对RedisTemplate的简单封装
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class TumoRedis {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ValueOperations<String, Object> valueOps;

    public TumoRedis(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOps = redisTemplate.opsForValue();
    }

    public void set(String key, Object value) {
        this.valueOps.set(key, value);
    }

    public void set(String key, Object value, Duration timeout) {
        this.valueOps.set(key, value, timeout);
    }

    public void set(String key, Object value, Long seconds) {
        this.valueOps.set(key, value, seconds, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return this.valueOps.get(key);
    }

}
