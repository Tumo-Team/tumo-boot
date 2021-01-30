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
public class AuthUtil {

    public final String ADMINISTRATOR = "administrator";

    /* 登录表单验证码Key标识 */
    public static final String CAPTCHA_FORM_KEY = "captcha";
    /* 登录验证码Header Key标识 */
    public static final String CAPTCHA_HEADER_KEY = "Captcha-Key";
    /* 验证码错误信息 */
    public static final String CAPTCHA_ERROR_INFO = "验证码不正确";

    /**
     * 获取Authentication对象
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户名
     */
    public String getUsername() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

    /**
     * 获取登录用户ID
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
