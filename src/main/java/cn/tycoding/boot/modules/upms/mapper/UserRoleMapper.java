package cn.tycoding.boot.modules.upms.mapper;

import cn.tycoding.boot.modules.upms.entity.Role;
import cn.tycoding.boot.modules.upms.entity.User;
import cn.tycoding.boot.modules.upms.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色关联表(UserRole)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-15 12:33:53
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据Role ID查询User集合
     *
     * @param roleId 角色ID
     */
    List<User> getUserListByRoleId(Long roleId);

    /**
     * 根据User ID查询Role集合
     *
     * @param userId 用户ID
     */
    List<Role> getRoleListByUserId(Long userId);
}
