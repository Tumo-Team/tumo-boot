package cn.tycoding.boot.common.auth.constant;

/**
 * API接口相关常量
 *
 * @author tycoding
 * @since 2020/10/18
 */
public interface ApiConstant {

    /**
     * 自定义OAuth Token端点地址
     */
    String API_OAUTH_TOKEN = "/tumo-boot/auth/oauth/token";

    /**
     * API接口前缀 - Auth模块
     */
    String API_AUTH_PREFIX = "/tumo-boot/auth";

    /**
     * API接口前缀 - System模块
     */
    String API_SYSTEM_PREFIX = "/tumo-boot/system";

    /**
     * API接口前缀 - Setting模块
     */
    String API_SETTING_PREFIX = "/tumo-boot/setting";

    /**
     * API接口前缀 - Blog模块
     */
    String API_BLOG_PREFIX = "/tumo-boot/blog";
}
