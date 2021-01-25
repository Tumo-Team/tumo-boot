package cn.tycoding.boot.common.auth.filter;

import cn.tycoding.boot.common.core.constant.CacheConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author tycoding
 * @since 2021/1/24
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String code = ServletRequestUtils.getStringParameter(request, CacheConstant.CAPTCHA_HEADER_KEY);
        System.out.println("验证码：" + code);
        chain.doFilter(request, response);
    }
}
