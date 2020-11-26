package cn.tycoding.boot.common.log.error;

import cn.tycoding.boot.common.core.api.HttpCode;
import cn.tycoding.boot.common.core.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.Servlet;

/**
 * @author tycoding
 * @since 2020/11/24
 */
@Slf4j
@Configuration
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@RestControllerAdvice
public class RestExceptionTranslator {

    public RestExceptionTranslator() {
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R handleError(NoHandlerFoundException e) {
        log.error(HttpCode.NOT_FOUND.getMsg(), e.getMessage());
        return new R(HttpCode.NOT_FOUND);
    }

}
