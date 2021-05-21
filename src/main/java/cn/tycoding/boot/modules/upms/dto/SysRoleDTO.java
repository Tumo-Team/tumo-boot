package cn.tycoding.boot.modules.upms.dto;

import cn.tycoding.boot.modules.upms.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * SysRole DTO封装
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDTO extends SysRole {
    private static final long serialVersionUID = -5792577217091151552L;

    /**
     * 菜单ID集合
     */
    private List<Long> menuIds;
}
