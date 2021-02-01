package cn.tycoding.boot.modules.upms.service.impl;

import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import cn.tycoding.boot.modules.upms.dto.UserDTO;
import cn.tycoding.boot.modules.upms.entity.*;
import cn.tycoding.boot.modules.upms.mapper.UserMapper;
import cn.tycoding.boot.modules.upms.mapper.UserRoleMapper;
import cn.tycoding.boot.modules.upms.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private final RoleService roleService;
    private final MenuService menuService;
    private final DeptService deptService;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    @Override
    public User findByName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public UserDTO findById(Long id) {
        User user = baseMapper.selectById(id);
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        Dept dept = deptService.getById(user.getDeptId());
        dto.setDeptName(dept == null ? null : dept.getName());
        return dto;
    }

    @Override
    public UserInfo info(String username) {
        return this.build(new UserInfo().setUser(this.findByName(username)));
    }

    @Override
    public List<Role> roleList(Long id) {
        return userRoleMapper.getRoleListByUserId(id);
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
        if (roleList.size() == 0) {
            throw new TumoOAuth2Exception(AuthUtil.NOT_ROLE_ERROR);
        }

        //获取用户权限列表
        List<Menu> menuList = menuService.getUserMenuList(roleList);
        Set<String> menuSet = menuList
                .stream()
                .filter(perm -> (perm.getPerms() != null && !"".equals(perm.getPerms())))
                .map(Menu::getPerms)
                .collect(Collectors.toSet());

        //获取用户部门信息
        Dept dept = deptService.getById(userInfo.getUser().getDeptId());

        return userInfo.setRoles(roleList).setPermissions(menuSet).setDept(dept);
    }

    @Override
    public List<User> list(User user) {
        return baseMapper.selectList(new LambdaQueryWrapper<User>().like(User::getUsername, user.getUsername()));
    }

    @Override
    public IPage<UserDTO> list(UserDTO user, QueryPage queryPage) {
        IPage<User> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        IPage<User> iPage = baseMapper.selectPage(page, new LambdaQueryWrapper<User>().ne(User::getId, AuthUtil.getUserId()));
        iPage.getRecords().forEach(i -> i.setPassword(null));
        IPage<UserDTO> result = new Page<>();
        BeanUtils.copyProperties(iPage, result);
        List<Dept> deptList = deptService.list();
        result.getRecords().forEach(i -> i.setDeptName(getDeptName(deptList, i.getDeptId())));
        return result;
    }

    private String getDeptName(List<Dept> deptList, Long id) {
        List<Dept> list = deptList.stream().filter(i -> i.getId().equals(id)).collect(Collectors.toList());
        return list.size() == 0 ? null : list.get(0).getName();
    }

    @Override
    public boolean checkName(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername());
        if (user.getId() != null && user.getId() != 0) {
            queryWrapper.ne(user.getId() != null, User::getId, user.getId());
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
