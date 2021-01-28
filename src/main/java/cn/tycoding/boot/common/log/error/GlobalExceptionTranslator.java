package cn.tycoding.boot.common.log.error;

import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.log.exception.ServiceException;
import cn.tycoding.boot.modules.auth.exception.TumoOAuth2Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
@RestControllerAdvice
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
public class GlobalExceptionTranslator {

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleError(ServiceException e) {
        log.error("业务异常", e);
        return R.fail(e.getHttpCode());
    }

    @ExceptionHandler({TumoOAuth2Exception.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(TumoOAuth2Exception e) {
        log.error("认证异常", e);
        return R.fail(e);
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(Throwable e) {
        log.error("服务器异常", e);
        return R.fail(e);
    }

}
