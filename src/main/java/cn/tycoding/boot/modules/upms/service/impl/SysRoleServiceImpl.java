package cn.tycoding.boot.modules.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysRoleMenu;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.mapper.SysRoleMapper;
import cn.tycoding.boot.modules.upms.mapper.SysRoleMenuMapper;
import cn.tycoding.boot.modules.upms.mapper.SysUserRoleMapper;
import cn.tycoding.boot.modules.upms.service.SysRoleMenuService;
import cn.tycoding.boot.modules.upms.service.SysRoleService;
import cn.tycoding.boot.modules.upms.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;
    private final SysUserRoleService sysUserRoleService;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRole> findRolesByUserId(Long id) {
        return sysUserRoleMapper.getRoleListByUserId(id);
    }

    @Override
    public List<SysRole> list(SysRole sysRole) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Tree<Object>> tree() {
        List<SysRole> list = this.list(new SysRole());
        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        list.forEach(t -> {
            TreeNode<Object> node = new TreeNode<>(
                    t.getId().toString(),
                    t.getParentId().toString(),
                    t.getName(),
                    0
            );
            node.setExtra(Dict.create().set("alias", t.getAlias()).set("des", t.getDes()));
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, "0");
    }

    @Override
    public Dict baseTree() {
        List<SysRole> list = this.list(new SysRole());
        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        list.forEach(t -> nodeList.add(
                new TreeNode<>(
                        t.getId().toString(),
                        t.getParentId().toString(),
                        t.getName(),
                        0
                )
        ));
        List<String> ids = list.stream().map(SysRole::getId).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.toList());
        List<Tree<Object>> tree = TreeUtil.build(nodeList, "0");
        return Dict.create().set("ids", ids).set("tree", tree);
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        List<SysRoleMenu> list = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        return list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public List<SysUser> userList(Long id) {
        return sysUserRoleMapper.getUserListByRoleId(id);
    }

    @Override
    public boolean checkName(SysRole sysRole) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>().eq(SysRole::getName, sysRole.getName());
        if (sysRole.getId() != null && sysRole.getId() != 0) {
            queryWrapper.ne(SysRole::getId, sysRole.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRole sysRole) {
        if (sysRole.getParentId() == null) {
            sysRole.setParentId(0L);
        }
        baseMapper.insert(sysRole);
    }

    @Override
    public void addPermission(List<Long> permissionList, Long id) {
        if (permissionList != null) {
            // 先删除原有的关联
            sysRoleMenuService.deleteRoleMenusByRoleId(id);

            // 再新增关联
            List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
            permissionList.forEach(menuId -> sysRoleMenuList.add(new SysRoleMenu()
                    .setMenuId(menuId)
                    .setRoleId(id)));
            sysRoleMenuService.saveBatch(sysRoleMenuList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRole sysRole) {
        baseMapper.updateById(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        SysRole sysRole = this.getById(id);
        if (AuthUtil.ADMINISTRATOR.equals(sysRole.getAlias())) {
            throw new RuntimeException("[超级管理员]角色不可删除");
        }
        baseMapper.deleteById(id);
        sysRoleMenuService.deleteRoleMenusByRoleId(id);
        sysUserRoleService.deleteUserRolesByRoleId(id);
    }
}
