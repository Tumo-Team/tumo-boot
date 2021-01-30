package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.core.utils.MenuTreeUtil;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:51
 */
@Service
@RequiredArgsConstructor
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
            node.setExtra(Dict.create()
                    .set("path", t.getPath())
                    .set("perms", t.getPerms())
                    .set("type", t.getType())
                    .set("icon", t.getIcon())
                    .set("component", t.getComponent())
                    .set("hidden", t.getHidden())
                    .set("frame", t.getFrame())
            );
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    public Dict baseTree() {
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
        List<Long> ids = list.stream().map(Menu::getId).collect(Collectors.toList());
        List<Tree<Object>> tree = TreeUtil.build(nodeList, 0L);
        return Dict.create().set("ids", ids).set("tree", tree);
    }

    @Override
    public List<MenuTree<Menu>> build() {
        List<Menu> menuList = baseMapper.build(AuthUtil.getUserId(), CommonConstant.MENU_TYPE_MENU);
        return MenuTreeUtil.build(menuList);
    }

    @Override
    public boolean checkName(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<Menu>().eq(Menu::getName, menu.getName());
        if (menu.getId() != null && menu.getId() != 0) {
            queryWrapper.ne(Menu::getId, menu.getId());
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
