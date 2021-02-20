package cn.tycoding.boot.modules.upms.service.impl;

import cn.tycoding.boot.modules.upms.entity.SysRoleMenu;
import cn.tycoding.boot.modules.upms.mapper.SysRoleMenuMapper;
import cn.tycoding.boot.modules.upms.service.SysRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色资源关联表(RoleMenu)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-15 12:34:09
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenusByRoleId(Long roleId) {
        baseMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenusByMenuId(Long menuId) {
        baseMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, menuId));
    }
}
