package cn.tycoding.boot.modules.upms.dto;

import cn.tycoding.boot.modules.upms.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对Menu 实体类的扩展
 *
 * @author tycoding
 * @since 2020/10/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends Menu {

    public static final String PATH_KEY = "path";
    public static final String PERMS_KEY = "perms";
    public static final String TYPE_KEY = "type";
    public static final String ICON_KEY = "icon";
    public static final String COMPONENT_KEY = "component";
    public static final String HIDDEN_KEY = "hidden";
    public static final String FRAME_KEY = "frame";
}
