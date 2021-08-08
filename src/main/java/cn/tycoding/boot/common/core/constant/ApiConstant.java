package cn.tycoding.boot.common.core.constant;

/**
 * API接口常量值
 *
 * @author tycoding
 * @since 2021/5/21
 */
public interface ApiConstant {

    /**
     * 基础API接口前缀
     */
    String API_BASE = "/tumo-boot";

    /**
     * 自定义OAuth Token端点地址
     */
    String API_OAUTH_TOKEN = "/tumo-boot/auth/oauth/token";

    /**
     * API接口前缀 - Auth模块
     */
    String API_AUTH_PREFIX = "/tumo-boot/auth";

    /**
     * API接口前缀 - Upms模块
     */
    String API_UPMS_PREFIX = "/tumo-boot/upms";

    /**
     * API接口前缀 - System模块
     */
    String API_SYSTEM_PREFIX = "/tumo-boot/system";
}
