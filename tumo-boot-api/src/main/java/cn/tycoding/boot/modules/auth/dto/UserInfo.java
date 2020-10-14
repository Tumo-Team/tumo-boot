package cn.tycoding.boot.modules.auth.dto;

import cn.tycoding.boot.modules.system.entity.Dept;
import cn.tycoding.boot.modules.system.entity.Role;
import cn.tycoding.boot.modules.system.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * @author tycoding
 * @since 2020/10/14
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    /**
     * 用户基本信息
     */
    private User user;

    /**
     * 用户所属部门
     */
    private Dept dept;

    /**
     * 用户角色列表
     */
    private List<Role> roles;

    /**
     * 用户权限标识
     */
    private Set<String> permissions;
}
