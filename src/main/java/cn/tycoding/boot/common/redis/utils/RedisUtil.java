package cn.tycoding.boot.common.redis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Redis工具类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Slf4j
public class RedisUtil {

    /**
     * 获取随机Key
     *
     * @return 随机字符串值
     */
    public static String getKey() {
        return UUID.randomUUID().toString();
    }

    /**
     * 根据指定Key前缀分页查询Key列表
     *
     * @param redisTemplate RedisTemplate对象
     * @param patternKey    指定前缀规则的Key
     * @param page          页码
     * @param limit         每页多少条
     * @return 分页后的列表
     */
    public static List<String> getKeysPage(RedisTemplate redisTemplate, String patternKey, int page, int limit) {
        List<String> list = new ArrayList<>();
        ScanOptions options = ScanOptions.scanOptions().count(1000).match(patternKey).build();
        RedisSerializer<String> redisSerializer = redisTemplate.getKeySerializer();

        Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection ->
                new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));

        if (cursor == null) {
            return list;
        }

        int flagIndex = 0;
        int startIndex = (page - 1) * limit;
        int endIndex = page * limit;
        while (cursor.hasNext()) {
            if (flagIndex >= startIndex && flagIndex < endIndex) {
                list.add(cursor.next().toString());
                flagIndex++;
                continue;
            }
            if (flagIndex >= endIndex) {
                break;
            }
            flagIndex++;
            cursor.next();
        }

        try {
            cursor.close();
        } catch (IOException e) {
            log.error("cursor关闭失败");
        }
        return list;
    }
}
