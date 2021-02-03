package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.modules.upms.entity.Menu;
import cn.tycoding.boot.modules.upms.entity.Role;
import cn.tycoding.boot.modules.upms.entity.RoleMenu;
import cn.tycoding.boot.modules.upms.entity.User;
import cn.tycoding.boot.modules.upms.mapper.RoleMapper;
import cn.tycoding.boot.modules.upms.mapper.RoleMenuMapper;
import cn.tycoding.boot.modules.upms.mapper.UserRoleMapper;
import cn.tycoding.boot.modules.upms.service.RoleMenuService;
import cn.tycoding.boot.modules.upms.service.RoleService;
import cn.tycoding.boot.modules.upms.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表(Role)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:23
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMenuService roleMenuService;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> findRolesByUserId(Long id) {
        return userRoleMapper.getRoleListByUserId(id);
    }

    @Override
    public List<Role> list(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Tree<Object>> tree() {
        List<Role> list = this.list(new Role());
        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        list.forEach(t -> {
            TreeNode<Object> node = new TreeNode<>(
                    t.getId(),
                    t.getParentId(),
                    t.getName(),
                    0
            );
            node.setExtra(Dict.create().set("alias", t.getAlias()).set("des", t.getDes()).set("createTime", DateUtil.formatDateTime(t.getCreateTime())));
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    public Dict baseTree() {
        List<Role> list = this.list(new Role());
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
        List<Long> ids = list.stream().map(Role::getId).collect(Collectors.toList());
        List<Tree<Object>> tree = TreeUtil.build(nodeList, 0L);
        return Dict.create().set("ids", ids).set("tree", tree);
    }

    @Override
    public List<Menu> getMenuListByRoleId(Long id) {
        return roleMenuMapper.getMenuListByRoleId(id);
    }

    @Override
    public List<User> userList(Long id) {
        return userRoleMapper.getUserListByRoleId(id);
    }

    @Override
    public boolean checkName(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>().eq(Role::getName, role.getName());
        if (role.getId() != null && role.getId() != 0) {
            queryWrapper.ne(Role::getId, role.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Role role) {
        if (role.getParentId() == null) {
            role.setParentId(0L);
        }
        role.setCreateTime(new Date());
        baseMapper.insert(role);
    }

    @Override
    public void addPermission(List<Long> permissionList, Long id) {
        if (permissionList != null) {
            // 先删除原有的关联
            roleMenuService.deleteRoleMenusByRoleId(id);

            // 再新增关联
            List<RoleMenu> roleMenuList = new ArrayList<>();
            permissionList.forEach(menuId -> roleMenuList.add(new RoleMenu()
                    .setMenuId(menuId)
                    .setRoleId(id)));
            roleMenuService.saveBatch(roleMenuList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role role) {
        baseMapper.updateById(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
        roleMenuService.deleteRoleMenusByRoleId(id);
        userRoleService.deleteUserRolesByRoleId(id);
    }
}
