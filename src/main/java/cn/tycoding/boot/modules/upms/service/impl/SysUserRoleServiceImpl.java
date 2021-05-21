package cn.tycoding.boot.modules.upms.service.impl;

import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.entity.SysUser;
import cn.tycoding.boot.modules.upms.entity.SysUserRole;
import cn.tycoding.boot.modules.upms.mapper.SysUserRoleMapper;
import cn.tycoding.boot.modules.upms.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户角色关联表(UserRole)表服务实现类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public List<SysUser> getUserListByRoleId(Long roleId) {
        return baseMapper.getUserListByRoleId(roleId);
    }

    @Override
    public List<SysRole> getRoleListByUserId(Long userId) {
        return baseMapper.getRoleListByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRolesByUserId(Long userId) {
        baseMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRolesByRoleId(Long roleId) {
        baseMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, roleId));
    }
}
