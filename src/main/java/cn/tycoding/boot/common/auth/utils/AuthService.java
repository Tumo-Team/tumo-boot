package cn.tycoding.boot.common.auth.utils;

import cn.tycoding.boot.common.auth.props.AuthProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 接口权限校验
 *
 * @author tycoding
 * @since 2021/6/11
 */
@AllArgsConstructor
public class AuthService {

    private final AuthProperties authProperties;

    /**
     * 校验当前登录的用户是否拥有指定权限
     *
     * @param perms 需要校验权限列表
     * @return 校验结果
     */
    public boolean hasAuth(String... perms) {
        if (perms.length == 0) {
            return false;
        }
        // 演示环境禁用操作
        if (authProperties.getIsDemoEnv() && AuthUtil.getRoleNames().contains(AuthUtil.DEMO_ENV)) {
            return false;
        }

        Authentication authentication = AuthUtil.getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).filter(StringUtils::hasText)
                .anyMatch(p -> PatternMatchUtils.simpleMatch(perms, p));
    }
}
