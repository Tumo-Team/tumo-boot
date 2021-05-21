package cn.tycoding.boot.modules.upms.dto;

import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * SysUser DTO封装
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDTO extends SysUser {
    private static final long serialVersionUID = 6173751370282753777L;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色信息
     */
    private List<SysRole> roles;

    /**
     * 角色ID列表
     */
    private List<Long> roleIds;
}
