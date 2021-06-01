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
     * OAuth缓存前缀
     */
    String OAUTH_PREFIX = REDIS_KEY_PREFIX + "auth:oauth:";

    /**
     * 验证码缓存前缀
     */
    String CAPTCHA_PREFIX = REDIS_KEY_PREFIX + "auth:captcha:";

    /**
     * 用户信息缓存
     */
    String USER_DETAIL_KEY = REDIS_KEY_PREFIX + "user_details";

    /**
     * 菜单权限缓存
     */
    String MENU_DETAIL_KEY = REDIS_KEY_PREFIX + "menu_details";
}
