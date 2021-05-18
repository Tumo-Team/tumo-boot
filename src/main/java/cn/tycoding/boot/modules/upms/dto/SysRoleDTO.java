package cn.tycoding.boot.modules.upms.dto;

import cn.tycoding.boot.modules.upms.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author tycoding
 * @since 2021/5/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDTO extends SysRole {

    /**
     * 菜单ID集合
     */
    private List<Long> menuIds;
}
