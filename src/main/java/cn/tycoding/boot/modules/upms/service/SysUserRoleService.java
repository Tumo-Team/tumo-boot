package cn.tycoding.boot.modules.upms.service;

import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户角色关联表(UserRole)表服务接口
 *
 * @author tycoding
 * @since 2020-10-15 12:33:51
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据RoleID查询User集合
     */
    List<SysUser> getUserListByRoleId(Long roleId);

    /**
     * 根据UserID查询Role集合
     */
    List<SysRole> getRoleListByUserId(Long userId);

    /**
     * 根据用户ID删除该用户的角色关联信息
     */
    void deleteUserRolesByUserId(Long userId);

    /**
     * 根据角色ID删除该用户的角色关联信息
     */
    void deleteUserRolesByRoleId(Long roleId);
}
