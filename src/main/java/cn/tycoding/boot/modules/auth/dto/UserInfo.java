package cn.tycoding.boot.modules.auth.dto;

import cn.tycoding.boot.modules.upms.entity.SysDept;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author tycoding
 * @since 2020/10/14
 */
@Data
@Accessors(chain = true)
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "用户基本信息")
    private SysUser user;

    /**
     * 用户所属部门
     */
    @ApiModelProperty(value = "用户所属部门")
    private SysDept dept;

    /**
     * 用户角色列表
     */
    @ApiModelProperty(value = "用户角色列表")
    private List<SysRole> roles;

    /**
     * 用户权限标识
     */
    @ApiModelProperty(value = "用户权限标识")
    private Set<String> permissions;
}
