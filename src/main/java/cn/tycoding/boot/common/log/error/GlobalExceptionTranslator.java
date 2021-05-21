package cn.tycoding.boot.common.log.error;

import cn.tycoding.boot.common.auth.utils.SpringContextHolder;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.log.event.LogEvent;
import cn.tycoding.boot.common.log.utils.SysLogUtil;
import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import cn.tycoding.boot.modules.upms.entity.SysLog;
import io.lettuce.core.RedisConnectionException;
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
 * 全局异常拦截（注意：这种方式只能拦截经过Controller的异常，未经过Controller的异常拦截不到）
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Slf4j
@Order
@Configuration
@RestControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
public class GlobalExceptionTranslator {

    @ExceptionHandler({TumoOAuth2Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(TumoOAuth2Exception e) {
        log.error("认证异常", e);
        SysLog sysLog = SysLogUtil.build(2, "认证异常", null, null);
        SpringContextHolder.publishEvent(new LogEvent(sysLog));
        return R.fail(e);
    }

    @ExceptionHandler({RedisConnectionFailureException.class, RedisConnectionException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(RedisConnectionFailureException e) {
        log.error("Redis连接异常", e);
        SysLog sysLog = SysLogUtil.build(2, "Redis连接异常", null, null);
        SpringContextHolder.publishEvent(new LogEvent(sysLog));
        return R.fail("Redis连接异常");
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(Throwable e) {
        log.error("服务器异常", e);
        SysLog sysLog = SysLogUtil.build(2, "服务器异常", null, null);
        SpringContextHolder.publishEvent(new LogEvent(sysLog));
        return R.fail(e);
    }

}
