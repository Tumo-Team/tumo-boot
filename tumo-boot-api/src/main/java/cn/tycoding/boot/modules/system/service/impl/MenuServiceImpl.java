package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.constant.AuthConstant;
import cn.tycoding.boot.common.utils.MenuTreeUtil;
import cn.tycoding.boot.modules.system.dto.MenuTree;
import cn.tycoding.boot.modules.system.entity.Menu;
import cn.tycoding.boot.modules.system.mapper.MenuMapper;
import cn.tycoding.boot.modules.system.service.MenuService;
import cn.tycoding.boot.modules.system.service.RoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:51
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;
    private final RoleMenuService roleMenuService;

    @Override
    public List<Menu> findPermissionsByUserId(Long id) {
        return menuMapper.findPermissionsByUserId(id);
    }

    @Override
    public List<MenuTree<Menu>> tree() {
        return MenuTreeUtil.build(baseMapper.selectList(new LambdaQueryWrapper<>()));
    }

    @Override
    public List<MenuTree<Menu>> build() {
        List<Menu> menuList = baseMapper.selectList(
                new LambdaQueryWrapper<Menu>()
                        .eq(Menu::getType, AuthConstant.MENU_TYPE_MENU));
        return MenuTreeUtil.build(menuList);
    }

    @Override
    public boolean checkName(Menu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            return false;
        }
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        if (menu.getId() != null && menu.getId() != 0) {
            queryWrapper.eq(Menu::getName, menu.getName());
            queryWrapper.ne(Menu::getId, menu.getId());
        } else {
            queryWrapper.eq(Menu::getName, menu.getName());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    public List<Menu> list(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Menu> list(Menu menu, QueryPage queryPage) {
        IPage<Menu> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(menu.getName()), Menu::getName, menu.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Menu menu) {
        baseMapper.insert(menu);
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        // TODO
        if (AuthConstant.MENU_TYPE_MENU.equals(menu.getType())) {
            menu.setPath(null);
            menu.setIcon(null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Menu menu) {
        baseMapper.updateById(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        roleMenuService.deleteRoleMenusByMenuId(id);
        baseMapper.changeTopNode(id);
    }
}
