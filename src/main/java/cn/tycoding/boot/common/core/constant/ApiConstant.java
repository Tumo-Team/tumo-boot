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
    String API_OAUTH_TOKEN = API_BASE+"/auth/oauth/token";

    /**
     * API接口前缀 - Auth模块
     */
    String API_AUTH_PREFIX = API_BASE+"/auth";

    /**
     * API接口前缀 - Upms模块
     */
    String API_UPMS_PREFIX = API_BASE+"/upms";

    /**
     * API接口前缀 - System模块
     */
    String API_SYSTEM_PREFIX = API_BASE+"/system";
}
