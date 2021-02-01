package cn.tycoding.boot.modules.upms.service;

import cn.hutool.core.lang.Dict;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.Menu;
import cn.tycoding.boot.modules.upms.entity.Role;
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
     * 构建菜单Tree树
     */
    List<MenuTree<Menu>> tree();

    /**
     * 获取菜单表基础数据
     */
    Dict baseTree();

    /**
     * 构建左侧权限菜单
     */
    List<MenuTree<Menu>> build();

    /**
     * 根据用户ID查询权限信息
     */
    List<Menu> getUserMenuList(List<Role> roleList);

    /**
     * 校验名称是否存在
     */
    boolean checkName(Menu menu);

    /**
     * 条件查询
     */
    List<Menu> list(Menu menu);

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
