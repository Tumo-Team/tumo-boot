package cn.tycoding.boot.common.auth.constant;

/**
 * API接口相关常量
 *
 * @author tycoding
 * @since 2020/10/18
 */
public interface ApiConstant {

    /**
     * 自定义授权登录页面
     */
    String API_LOGIN_PAGE = "/tumo-boot/auth/login";

    /**
     * 自定义授权登录接口
     */
    String API_LOGIN_FORM = "/tumo-boot/auth/form";

    /**
     * 自定义OAuth Token端点地址
     */
    String API_OAUTH_TOKEN = "/tumo-boot/auth/oauth/token";

    /**
     * API接口前缀 - Oss模块
     */
    String API_OSS_PREFIX = "/tumo-boot/oss";

    /**
     * API接口前缀 - Auth模块
     */
    String API_AUTH_PREFIX = "/tumo-boot/auth";

    /**
     * API接口前缀 - Upms模块
     */
    String API_UPMS_PREFIX = "/tumo-boot/upms";

}
