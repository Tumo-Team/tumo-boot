package cn.tycoding.boot.modules.system.service.impl;

import cn.tycoding.boot.common.api.QueryPage;
import cn.tycoding.boot.common.utils.MenuTreeUtil;
import cn.tycoding.boot.common.utils.SecurityUtil;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.system.dto.MenuTree;
import cn.tycoding.boot.modules.system.dto.UserDTO;
import cn.tycoding.boot.modules.system.entity.*;
import cn.tycoding.boot.modules.system.mapper.MenuMapper;
import cn.tycoding.boot.modules.system.mapper.UserMapper;
import cn.tycoding.boot.modules.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author tycoding
 * @since 2020-10-14 14:32:27
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private final RoleService roleService;
    private final MenuService menuService;
    private final DeptService deptService;
    private final UserRoleService userRoleService;
    private final MenuMapper menuMapper;

    @Override
    public User findByName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public UserDTO findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public UserInfo info(String username) {
        return this.build(new UserInfo().setUser(this.findByName(username)));
    }

    @Override
    public List<MenuTree<Menu>> getMenuByUserId(Long id) {
        return MenuTreeUtil.build(menuMapper.findPermissionsByUserId(id));
    }

    @Override
    public List<Long> roleList(Long id) {
        return baseMapper.roleList(id);
    }

    /**
     * 构建用户信息、角色信息、权限标识信息、部门信息
     */
    private UserInfo build(UserInfo userInfo) {
        if (userInfo == null || userInfo.getUser() == null) {
            throw new RuntimeException("没有查询用用户信息");
        }
        //获取用户角色列表
        List<Role> roleList = roleService.findRolesByUserId(userInfo.getUser().getId());

        //获取用户权限列表
        List<Menu> menuList = menuService.findPermissionsByUserId(userInfo.getUser().getId());
        Set<String> menuSet = menuList
                .stream()
                .filter(perm -> (perm.getPerms() != null && !"".equals(perm.getPerms())))
                .map(Menu::getPerms)
                .collect(Collectors.toSet());

        //获取用户部门信息
        Dept dept = deptService.getById(userInfo.getUser().getDeptId());

        return userInfo
                .setRoles(roleList)
                .setPermissions(menuSet)
                .setDept(dept);
    }

    @Override
    public List<UserDTO> list(UserDTO user) {
        return baseMapper.filterList(user);
    }

    @Override
    public IPage<UserDTO> list(UserDTO user, QueryPage queryPage) {
        IPage<User> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return baseMapper.list(page, user, SecurityUtil.getUserId());
    }

    @Override
    public boolean checkName(User user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return false;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (user.getId() != null && user.getId() != 0) {
            queryWrapper.eq(User::getUsername, user.getUsername());
            queryWrapper.ne(User::getId, user.getId());
        } else {
            queryWrapper.eq(User::getUsername, user.getUsername());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserDTO user) {
        user.setCreateTime(new Date());
        PASSWORD_ENCODER.encode(user.getPassword());
        baseMapper.insert(user);
    }

    @Override
    public void addRole(List<Long> roleList, Long id) {
        if (roleList != null) {
            // 删除之前用户与角色表之前的关联，并重新建立关联
            userRoleService.deleteUserRolesByUserId(id);

            // 新增用户角色关联
            List<UserRole> list = new ArrayList<>();
            roleList.forEach(roleId -> list.add(new UserRole()
                    .setUserId(id)
                    .setRoleId(roleId)));
            userRoleService.saveBatch(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDTO user) {
        user.setPassword(null);
        baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
        userRoleService.deleteUserRolesByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPass(User user) {
        baseMapper.updateById(new User().setId(user.getId()).setPassword(user.getPassword()));
    }
}
