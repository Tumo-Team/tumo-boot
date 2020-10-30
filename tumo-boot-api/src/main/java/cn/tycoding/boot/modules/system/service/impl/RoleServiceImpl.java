package cn.tycoding.boot.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.modules.system.dto.RoleDTO;
import cn.tycoding.boot.modules.system.entity.Role;
import cn.tycoding.boot.modules.system.entity.RoleMenu;
import cn.tycoding.boot.modules.system.entity.User;
import cn.tycoding.boot.modules.system.mapper.RoleMapper;
import cn.tycoding.boot.modules.system.mapper.UserRoleMapper;
import cn.tycoding.boot.modules.system.service.RoleMenuService;
import cn.tycoding.boot.modules.system.service.RoleService;
import cn.tycoding.boot.modules.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色表(Role)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:45:23
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMenuService roleMenuService;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findRolesByUserId(Long id) {
        return baseMapper.findRolesByUserId(id);
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
            HashMap<String, Object> map = new HashMap<>();
            map.put(RoleDTO.ALIAS_KEY, t.getAlias());
            map.put(RoleDTO.DES_KEY, t.getDes());
            map.put(RoleDTO.CREATE_TIME_KEY, DateUtil.formatDateTime(t.getCreateTime()));
            node.setExtra(map);
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    public Map<String, Object> baseTree() {
        Map<String, Object> map = new HashMap<>();
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

        map.put("ids", list.stream().map(Role::getId).collect(Collectors.toList()));
        map.put("tree", TreeUtil.build(nodeList, 0L));
        return map;
    }

    @Override
    public List<Long> menuList(Long id) {
        return baseMapper.menuList(id);
    }

    @Override
    public List<User> userList(Long id) {
        return userRoleMapper.selectUserList(id);
    }

    @Override
    public boolean checkName(Role role) {
        if (StringUtils.isBlank(role.getName())) {
            return false;
        }
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (role.getId() != null && role.getId() != 0) {
            queryWrapper.eq(Role::getName, role.getName());
            queryWrapper.ne(Role::getId, role.getId());
        } else {
            queryWrapper.eq(Role::getName, role.getName());
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
