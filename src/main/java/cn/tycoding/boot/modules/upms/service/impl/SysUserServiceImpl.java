package cn.tycoding.boot.modules.upms.service.impl;

import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.constant.CacheConstant;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.core.utils.BeanUtil;
import cn.tycoding.boot.common.core.utils.Is;
import cn.tycoding.boot.common.mybatis.utils.MybatisUtil;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import cn.tycoding.boot.modules.upms.dto.SysUserDTO;
import cn.tycoding.boot.modules.upms.entity.*;
import cn.tycoding.boot.modules.upms.mapper.SysUserMapper;
import cn.tycoding.boot.modules.upms.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private final SysRoleService sysRoleService;
    private final SysMenuService sysMenuService;
    private final SysDeptService sysDeptService;
    private final SysUserRoleService sysUserRoleService;

    @Override
    public SysUser findByName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public SysUserDTO findById(Long userId) {
        SysUser sysUser = baseMapper.selectById(userId);
        SysUserDTO dto = BeanUtil.copy(sysUser, SysUserDTO.class);
        dto.setPassword(null);
        List<Long> roleIds = sysUserRoleService.getRoleListByUserId(userId).stream().map(SysRole::getId).collect(Collectors.toList());
        dto.setRoleIds(roleIds);
        return dto;
    }

    @Override
    @Cacheable(value = CacheConstant.USER_DETAIL_KEY, key = "#username")
    public UserInfo info(String username) {
        return this.build(new UserInfo().setUser(this.findByName(username)));
    }

    /**
     * 构建用户信息、角色信息、权限标识信息、部门信息
     */
    private UserInfo build(UserInfo userInfo) {
        if (userInfo == null || userInfo.getUser() == null) {
            throw new RuntimeException("没有查询用用户信息");
        }
        //获取用户角色列表
        List<SysRole> sysRoleList = sysRoleService.findRolesByUserId(userInfo.getUser().getId());
        if (sysRoleList.size() == 0) {
            throw new TumoOAuth2Exception(AuthUtil.NOT_ROLE_ERROR);
        }

        //获取用户权限列表
        List<SysMenu> menuList = new ArrayList<>();
        long isAdmin = sysRoleList.stream().filter(role -> AuthUtil.ADMINISTRATOR.equals(role.getAlias())).count();
        if (isAdmin > 0) {
            // 包含了超级管理员角色，拥有所有权限
            menuList = sysMenuService.list();
        } else {
            // 根据角色筛选权限
            menuList = sysMenuService.getUserMenuList(sysRoleList);
        }
        Set<String> perms = menuList
                .stream()
                .map(SysMenu::getPerms)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toSet());

        //获取用户部门信息
        SysDept sysDept = sysDeptService.getById(userInfo.getUser().getDeptId());

        return userInfo.setRoles(sysRoleList).setPerms(perms).setDept(sysDept);
    }

    @Override
    public List<SysUser> list(SysUser sysUser) {
        List<SysUser> list = baseMapper.selectList(new LambdaQueryWrapper<SysUser>()
                .like(StringUtils.isNotEmpty(sysUser.getUsername()), SysUser::getUsername, sysUser.getUsername()));
        list.forEach(i -> i.setPassword(null));
        return list;
    }

    @Override
    public IPage<SysUserDTO> page(SysUserDTO user, QueryPage queryPage) {
        return baseMapper.page(MybatisUtil.wrap(user, queryPage), user, AuthUtil.getUserId());
    }

    @Override
    public boolean checkName(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUser.getUsername());
        if (sysUser.getId() != null && sysUser.getId() != 0) {
            queryWrapper.ne(sysUser.getId() != null, SysUser::getId, sysUser.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysUserDTO user) {
        user.setCreateTime(new Date());
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));

        // 设置默认头像
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(CommonConstant.DEFAULT_AVATAR);
        }

        // 设置角色
        if (Is.isEmpty(user.getRoleIds())) {
            throw new RuntimeException("用户角色不能为空");
        }
        baseMapper.insert(user);
        addRole(user);
    }

    private void addRole(SysUserDTO user) {
        List<Long> roleIds = user.getRoleIds();
        Long userId = user.getId();
        if (roleIds != null) {
            // 删除之前用户与角色表之前的关联，并重新建立关联
            sysUserRoleService.deleteUserRolesByUserId(userId);

            // 新增用户角色关联
            List<SysUserRole> list = new ArrayList<>();
            roleIds.forEach(roleId -> list.add(new SysUserRole()
                    .setUserId(userId)
                    .setRoleId(roleId)));
            sysUserRoleService.saveBatch(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstant.USER_DETAIL_KEY, key = "#user.username")
    public void update(SysUserDTO user) {
        // 设置默认头像
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(CommonConstant.DEFAULT_AVATAR);
        }

        // 设置角色
        addRole(user);
        user.setPassword(null);
        baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstant.USER_DETAIL_KEY, key = "#user.username")
    public void delete(Long id) {
        baseMapper.deleteById(id);
        sysUserRoleService.deleteUserRolesByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPass(SysUser sysUser) {
        baseMapper.updateById(new SysUser()
                .setId(sysUser.getId())
                .setPassword(PASSWORD_ENCODER.encode(sysUser.getPassword())));
    }
}
