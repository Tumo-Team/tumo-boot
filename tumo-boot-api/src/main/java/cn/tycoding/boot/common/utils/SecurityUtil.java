package cn.tycoding.boot.common.utils;

import cn.tycoding.boot.modules.auth.dto.TumoBootUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@UtilityClass
public class SecurityUtil {

    /**
     * 获取Authentication对象
     *
     * @return
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public String getUsername() {
        Authentication authentication = getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof TumoBootUser)) {
            return (String) principal;
        }
        return null;
    }
}
