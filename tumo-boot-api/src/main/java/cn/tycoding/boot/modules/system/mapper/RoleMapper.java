package cn.tycoding.boot.modules.system.mapper;

import cn.tycoding.boot.modules.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRolesByUserId(Long id);

    List<Long> menuList(Long id);
}
