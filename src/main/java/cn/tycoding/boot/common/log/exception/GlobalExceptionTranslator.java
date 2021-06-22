package cn.tycoding.boot.common.log.exception;

import cn.tycoding.boot.common.auth.props.AuthProperties;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.common.core.api.HttpCode;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.log.utils.SysLogUtil;
import cn.tycoding.boot.modules.auth.exception.TumoAuth2Exception;
import io.lettuce.core.RedisConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截（注意：这种方式只能拦截经过Controller的异常，未经过Controller的异常拦截不到）
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionTranslator {

    private final AuthProperties authProperties;

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleError(ServiceException e) {
        log.error("----------业务异常----------");
        e.printStackTrace();
        SysLogUtil.publish(SysLogUtil.TYPE_FAIL, "业务异常");
        return R.fail(e.getHttpCode().getCode(), e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R handleError(AccessDeniedException e) {
        log.error("----------没有访问权限----------");
        e.printStackTrace();
        if (authProperties.getIsDemoEnv() && AuthUtil.getRoleNames().contains(AuthUtil.DEMO_ENV)) {
            SysLogUtil.publish(SysLogUtil.TYPE_FAIL, "演示环境，请勿操作!");
            return R.fail(HttpCode.FORBIDDEN.getCode(), "演示环境，请勿操作!");
        }
        SysLogUtil.publish(SysLogUtil.TYPE_FAIL, "没有访问权限");
        return R.fail(HttpCode.FORBIDDEN);
    }

    @ExceptionHandler({TumoAuth2Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(TumoAuth2Exception e) {
        log.error("----------认证异常----------");
        e.printStackTrace();
        SysLogUtil.publish(SysLogUtil.TYPE_FAIL, "认证异常");
        return R.fail(e);
    }

    @ExceptionHandler({RedisConnectionFailureException.class, RedisConnectionException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(RedisConnectionFailureException e) {
        log.error("----------Redis连接异常----------");
        e.printStackTrace();
        SysLogUtil.publish(SysLogUtil.TYPE_FAIL, "Redis连接异常");
        return R.fail("Redis连接异常");
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(Throwable e) {
        log.error("----------服务器异常----------");
        e.printStackTrace();
        SysLogUtil.publish(SysLogUtil.TYPE_FAIL, "服务器异常");
        return R.fail(e);
    }

}
