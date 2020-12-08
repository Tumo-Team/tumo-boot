package cn.tycoding.boot.modules.system.mapper;

import cn.tycoding.boot.modules.system.entity.User;
import cn.tycoding.boot.modules.system.entity.UserRole;
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

    List<User> selectUserList(Long roleId);
}
