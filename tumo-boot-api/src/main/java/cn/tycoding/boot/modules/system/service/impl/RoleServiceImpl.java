package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.modules.system.dto.RoleWithMenu;
import cn.tycoding.boot.modules.system.entity.Role;
import cn.tycoding.boot.modules.system.entity.RoleMenu;
import cn.tycoding.boot.modules.system.mapper.RoleMapper;
import cn.tycoding.boot.modules.system.service.RoleMenuService;
import cn.tycoding.boot.modules.system.service.RoleService;
import cn.tycoding.boot.modules.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public IPage<Role> list(Role role, QueryPage queryPage) {
        IPage<Role> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(role.getName()), Role::getName, role.getName());
        return baseMapper.selectPage(page, queryWrapper);
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
    public void add(RoleWithMenu roleWithMenu) {
        roleWithMenu.setCreateTime(new Date());
        baseMapper.insert(roleWithMenu);
        this.addRoleMenu(roleWithMenu);
    }

    private void addRoleMenu(RoleWithMenu sysRole) {
        if (sysRole.getMenuIds() != null && sysRole.getMenuIds().get(0) != null) {
            sysRole.getMenuIds().forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(sysRole.getId());
                roleMenuService.save(roleMenu);
            });
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleWithMenu roleWithMenu) {
        baseMapper.updateById(roleWithMenu);
        roleMenuService.deleteRoleMenusByRoleId(roleWithMenu.getId());
        addRoleMenu(roleWithMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
        roleMenuService.deleteRoleMenusByRoleId(id);
        userRoleService.deleteUserRolesByRoleId(id);
    }
}
