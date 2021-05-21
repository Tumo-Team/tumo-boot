package cn.tycoding.boot.common.redis.component;

import cn.tycoding.boot.common.redis.config.TumoRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 自定义Redis配置
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Component
@EnableCaching
@RequiredArgsConstructor
public class RedisComponent {

    private final RedisConnectionFactory connectionFactory;

    @Bean(name = {"redisTemplate"})
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public TumoRedis tumoRedis(RedisTemplate<String, Object> redisTemplate) {
        return new TumoRedis(redisTemplate);
    }
}
