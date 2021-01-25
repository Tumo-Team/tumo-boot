package cn.tycoding.boot.common.core.constant;

/**
 * 缓存常量
 * @author tycoding
 * @since 2021/1/24
 */
public interface CacheConstant {

    /**
     * 系统所有Redis缓存Key前缀 prefix
     */
    String REDIS_KEY_PREFIX = "tumo-boot:";

    /**
     * OAuth Redis Key
     */
    String OAUTH_REDIS_KEY = REDIS_KEY_PREFIX + "oauth:";

    /**
     * Captcha Redis Key
     */
    String CAPTCHA_REDIS_KEY = REDIS_KEY_PREFIX + "auth:captcha:";

    /**
     * Captcha Header Key
     */
    String CAPTCHA_HEADER_KEY = "Captcha-Key";

}
