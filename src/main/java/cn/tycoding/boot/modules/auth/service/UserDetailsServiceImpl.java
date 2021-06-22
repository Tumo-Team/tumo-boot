package cn.tycoding.boot.modules.auth.service;

import cn.tycoding.boot.common.core.constant.AuthConstant;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.constant.CacheConstant;
import cn.tycoding.boot.modules.auth.dto.TumoUser;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.auth.exception.TumoAuth2Exception;
import cn.tycoding.boot.modules.upms.entity.SysRole;
import cn.tycoding.boot.modules.upms.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 封装授权时如何加载用户信息
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 加载用户信息，在这里可做登录用户的权限、角色判断
     *
     * @param username 用户名
     * @return UserDetails对象
     * @throws UsernameNotFoundException 用户名没有找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cache cache = cacheManager.getCache(CacheConstant.USER_DETAIL_KEY);
        if (cache != null && cache.get(username) != null) {
            UserInfo info = (UserInfo) cache.get(username).get();
            return getUserDetails(info);
        }
        UserInfo info = sysUserService.info(username);
        return getUserDetails(info);
    }

    private UserDetails getUserDetails(UserInfo userInfo) {
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Set<String> authSet = new HashSet<>();

        List<SysRole> sysRoles = userInfo.getRoles();
        if (sysRoles == null || sysRoles.size() == 0) {
            throw new TumoAuth2Exception(AuthUtil.NOT_ROLE_ERROR);
        }
        sysRoles.forEach(role -> authSet.add(AuthConstant.ROLE_PREFIX + role.getId() + AuthConstant.ROLE_SUFFIX + role.getAlias()));

        Set<String> perms = userInfo.getPerms();
        if (perms != null && perms.size() > 0) {
            authSet.addAll(perms);
        }

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authSet.toArray(new String[0]));

        return new TumoUser(userInfo.getUser().getId(),
                userInfo.getDept() == null ? null : userInfo.getDept().getId(),
                userInfo.getUser().getUsername(),
                userInfo.getUser().getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
