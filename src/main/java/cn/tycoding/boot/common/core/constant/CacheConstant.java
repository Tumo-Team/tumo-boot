package cn.tycoding.boot.common.core.constant;

/**
 * 缓存常量
 *
 * @author tycoding
 * @since 2021/5/21
 */
public interface CacheConstant {

    /**
     * 系统所有Redis缓存Key前缀 prefix
     */
    String REDIS_KEY_PREFIX = "tumo-boot:";

    /**
     * OAuth Cache Key
     */
    String OAUTH_KEY = REDIS_KEY_PREFIX + "auth:oauth:";

    /**
     * Captcha Cache Key
     */
    String CAPTCHA_KEY = REDIS_KEY_PREFIX + "auth:captcha:";

    /**
     * User Cache LKey
     */
    String USER_DETAIL_KEY = REDIS_KEY_PREFIX + "user_details:";

    /**
     * Menu Cache LKey
     */
    String MENU_DETAIL_KEY = REDIS_KEY_PREFIX + "menu_details:";
}
