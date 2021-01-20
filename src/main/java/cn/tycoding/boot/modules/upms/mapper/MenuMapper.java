package cn.tycoding.boot.modules.upms.mapper;

import cn.tycoding.boot.modules.upms.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author tycoding
 * @since 2020-10-14 14:45:55
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findPermissionsByUserId(Long id);

    void changeTopNode(Long id);

    List<Menu> build(@Param("userId") Long userId, @Param("type") String type);
}
