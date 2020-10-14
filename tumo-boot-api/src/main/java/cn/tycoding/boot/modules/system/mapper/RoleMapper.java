package cn.tycoding.boot.modules.system.mapper;

import cn.tycoding.boot.modules.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:45:26
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
