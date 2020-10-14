package cn.tycoding.boot.modules.system.service;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.entity.Menu;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单表(Menu)表服务接口
 *
 * @author tycoding
 * @since 2020-10-14 14:45:51
 */
public interface MenuService extends IService<Menu> {

    /**
     * 条件查询
     */
    List<Menu> list(Menu menu);

    /**
     * 分页、条件查询
     */
    IPage<Menu> list(Menu menu, QueryPage queryPage);

    /**
     * 新增
     */
    void add(Menu menu);

    /**
     * 修改
     */
    void update(Menu menu);

    /**
     * 删除
     */
    void delete(Long id);
}
