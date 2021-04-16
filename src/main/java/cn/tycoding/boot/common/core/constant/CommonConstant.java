package cn.tycoding.boot.common.core.constant;

/**
 * 系统公共常量定义
 *
 * @author tycoding
 * @since 2020/10/9
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
     * 分页结果集中承载分页数据的属性名
     */
    String PAGE_ROWS = "rows";

    /**
     * 分页结果集中总数量total名称
     */
    String PAGE_TOTAL = "total";

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
}
