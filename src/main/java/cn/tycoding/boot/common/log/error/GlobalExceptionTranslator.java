package cn.tycoding.boot.common.log.error;

import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * @author tycoding
 * @since 2020/11/23
 */
@Slf4j
@Order
@Configuration
@RequiredArgsConstructor
@RestControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
public class GlobalExceptionTranslator {

    @ExceptionHandler({TumoOAuth2Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(TumoOAuth2Exception e) {
        log.error("认证异常", e);
        return R.fail(e);
    }

    @ExceptionHandler({RedisConnectionFailureException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(RedisConnectionFailureException e) {
        log.error("Redis连接异常", e);
        return R.fail("Redis连接异常");
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(Throwable e) {
        log.error("服务器异常", e);
        return R.fail(e);
    }

}
