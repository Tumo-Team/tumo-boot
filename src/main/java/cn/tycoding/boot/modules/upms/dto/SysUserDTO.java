package cn.tycoding.boot.modules.upms.dto;

import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 对User实体类的扩展
 *
 * @author tycoding
 * @since 2020/10/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDTO extends SysUser {

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色信息
     */
    private List<SysRole> roles;
}
