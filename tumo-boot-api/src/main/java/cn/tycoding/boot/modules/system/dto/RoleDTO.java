package cn.tycoding.boot.modules.system.dto;

import cn.tycoding.boot.modules.system.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对Role 实体类的扩展
 *
 * @author tycoding
 * @since 2020/10/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends Role {

    public static final String ALIAS_KEY = "alias";
    public static final String DES_KEY = "des";
    public static final String CREATE_TIME_KEY = "createTime";
}
