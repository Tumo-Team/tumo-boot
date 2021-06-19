package cn.tycoding.boot.common.core.constant;

/**
 * 系统公共常量
 *
 * @author tycoding
 * @since 2021/5/21
 */
public interface CommonConstant {

    /**
     * UTF-8 编码
     */
    String UTF_8 = "utf-8";

    /**
     * JSON 请求响应格式
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 菜单类型：menu
     */
    String MENU_TYPE_MENU = "menu";

    /**
     * 菜单类型：button
     */
    String MENU_TYPE_BUTTON = "button";

    /**
     * 菜单：默认Icon图标
     */
    String MENU_ICON = "alert";

    /**
     * 默认用户头像路径
     */
    String DEFAULT_AVATAR = "/upload/default.png";
}
