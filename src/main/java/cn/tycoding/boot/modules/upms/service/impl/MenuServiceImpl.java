package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.common.auth.utils.SecurityUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.core.utils.MenuTreeUtil;
import cn.tycoding.boot.modules.upms.dto.MenuDTO;
import cn.tycoding.boot.modules.upms.dto.MenuTree;
import cn.tycoding.boot.modules.upms.entity.Menu;
import cn.tycoding.boot.modules.upms.mapper.MenuMapper;
import cn.tycoding.boot.modules.upms.service.MenuService;
import cn.tycoding.boot.modules.upms.service.RoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Tree<Object>> tree() {
        List<Menu> list = this.list(new Menu());
        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        list.forEach(t -> {
            TreeNode<Object> node = new TreeNode<>(
                    t.getId(),
                    t.getParentId(),
                    t.getName(),
                    0
            );
            HashMap<String, Object> map = new HashMap<>();
            map.put(MenuDTO.PATH_KEY, t.getPath());
            map.put(MenuDTO.PERMS_KEY, t.getPerms());
            map.put(MenuDTO.TYPE_KEY, t.getType());
            map.put(MenuDTO.ICON_KEY, t.getIcon());
            map.put(MenuDTO.COMPONENT_KEY, t.getComponent());
            map.put(MenuDTO.HIDDEN_KEY, t.getHidden());
            map.put(MenuDTO.FRAME_KEY, t.getFrame());
            node.setExtra(map);
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    public Map<String, Object> baseTree() {
        Map<String, Object> map = new HashMap<>();
        List<Menu> list = this.list(new Menu());
        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        list.forEach(t -> nodeList.add(
                new TreeNode<>(
                        t.getId(),
                        t.getParentId(),
                        t.getName(),
                        0
                )
        ));

        map.put("ids", list.stream().map(Menu::getId).collect(Collectors.toList()));
        map.put("tree", TreeUtil.build(nodeList, 0L));
        return map;
    }

    @Override
    public List<MenuTree<Menu>> build() {
        List<Menu> menuList = baseMapper.build(SecurityUtil.getUserId(), CommonConstant.MENU_TYPE_MENU);
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
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        if (CommonConstant.MENU_TYPE_MENU.equals(menu.getType()) && menu.getIcon() == null) {
            menu.setIcon(CommonConstant.MENU_ICON);
        }
        if (CommonConstant.MENU_TYPE_BUTTON.equals(menu.getType())) {
            menu.setPath(null);
            menu.setIcon(null);
        }
        baseMapper.insert(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        if (CommonConstant.MENU_TYPE_BUTTON.equals(menu.getType())) {
            menu.setPath(null);
            menu.setIcon(null);
        }
        baseMapper.updateById(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        roleMenuService.deleteRoleMenusByMenuId(id);
        baseMapper.changeTopNode(id);
        baseMapper.deleteById(id);
    }
}
