package cn.tycoding.boot.common.auth.utils;

import cn.tycoding.boot.modules.auth.dto.TumoUser;
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
     * @return username
     */
    public String getUsername() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

    /**
     * 获取用户ID
     *
     * @return id
     */
    public Long getUserId() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof TumoUser) {
            return ((TumoUser) principal).getId();
        }
        return null;
    }
}
