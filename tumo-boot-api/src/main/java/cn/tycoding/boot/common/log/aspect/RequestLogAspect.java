package cn.tycoding.boot.common.log.aspect;

import cn.hutool.json.JSONObject;
import cn.tycoding.boot.common.auth.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tycoding
 * @since 2020/11/10
 */
@Slf4j
@Aspect
@Configuration
public class RequestLogAspect {

    @Around("execution(!static cn.tycoding.boot.common.core.api.R *(..)) && (@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = WebUtil.getRequest();
        Object result = point.proceed();
        if (request == null) {
            return result;
        }
        String beforeLog = "\n\n================  Request Start  ================" +
                "\n===General=== Request URL: " + request.getRequestURL() +
                "\n===General=== Request Method: " + request.getMethod() +
                "\n===General=== Remote Address: " + request.getRemoteAddr() +
                "\n\n===Headers=== Accept: " + request.getHeader(HttpHeaders.ACCEPT) +
                "\n===Headers=== Authorization: " + request.getHeader(HttpHeaders.AUTHORIZATION) +
                "\n===Headers=== Host: " + request.getHeader(HttpHeaders.HOST) +
                "\n===Headers=== User-Agent: " + request.getHeader(HttpHeaders.USER_AGENT) +
                "\n===Headers=== Parameters: " + new JSONObject(request.getParameterMap()).toString() +
                "\n\n===Result=== " + new JSONObject(result).toString() +
                "\n================  Request End  ================\n";
        log.info(beforeLog);
        return result;
    }
}
