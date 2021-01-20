package cn.tycoding.boot.modules.auth.service;

import cn.tycoding.boot.common.auth.constant.AuthConstant;
import cn.tycoding.boot.modules.auth.dto.TumoUser;
import cn.tycoding.boot.modules.auth.dto.UserInfo;
import cn.tycoding.boot.modules.upms.entity.Role;
import cn.tycoding.boot.modules.upms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author tycoding
 * @since 2020/10/14
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 加载用户信息，在这里可做登录用户的权限、角色判断
     *
     * @param username
     * @return UserDetails对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername >>>>>>>>>>>>> username: {}", username);
        UserInfo info = userService.info(username);
        log.info("loadUserByUsername >>>>>>>>>>>>>> info: {}", info);
        return getUserDetails(info);
    }

    private UserDetails getUserDetails(UserInfo userInfo) {
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Set<String> authSet = new HashSet<>();

        List<Role> roles = userInfo.getRoles();
        if (roles != null && roles.size() > 0) {
            roles.forEach(role -> authSet.add(AuthConstant.ROLE_PREFIX + role));
        }
//        Set<String> permissions = userInfo.getPermissions();
//        if (permissions != null && permissions.size() > 0) {
//            authSet.addAll(permissions);
//        }
//        String perms = permissions.stream().map(String::trim).collect(Collectors.joining(","));

        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authSet.toString());

        return new TumoUser(userInfo.getUser().getId(),
                userInfo.getUser().getUsername(),
                userInfo.getUser().getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
