package cn.tycoding.boot.modules.upms.mapper;

import cn.tycoding.boot.modules.upms.entity.Menu;
import cn.tycoding.boot.modules.upms.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色资源关联表(RoleMenu)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-15 12:34:11
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Menu> getMenuListByRoleId(Long roleId);
}
