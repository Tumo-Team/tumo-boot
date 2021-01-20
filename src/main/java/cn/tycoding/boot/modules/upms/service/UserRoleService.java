package cn.tycoding.boot.modules.upms.service;

import cn.tycoding.boot.modules.upms.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户角色关联表(UserRole)表服务接口
 *
 * @author tycoding
 * @since 2020-10-15 12:33:51
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 根据用户ID删除该用户的角色关联信息
     */
    void deleteUserRolesByUserId(Long userId);

    /**
     * 根据角色ID删除该用户的角色关联信息
     */
    void deleteUserRolesByRoleId(Long roleId);
}
