package cn.tycoding.boot.common.log.aspect;

import cn.hutool.json.JSONObject;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局拦截Controller接口，打印请求日志
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Slf4j
@Aspect
@Configuration
@ConditionalOnProperty(value = {"tumo-boot.log.enable"}, matchIfMissing = true)
public class RequestLogAspect {

    @Around("(@within(org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint)) || (execution(!static cn.tycoding.boot.common.core.api.R *(..)) && (@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = AuthUtil.getRequest();
        String beforeLog = "\n\n================  Request Start  ================" +
                "\n===General=== Request URL: " + request.getRequestURL() +
                "\n===General=== Request Method: " + request.getMethod() +
                "\n===General=== Remote Address: " + request.getRemoteAddr() +
                "\n\n===Headers=== Accept: " + request.getHeader(HttpHeaders.ACCEPT) +
                "\n===Headers=== Authorization: " + request.getHeader(HttpHeaders.AUTHORIZATION) +
                "\n===Headers=== Host: " + request.getHeader(HttpHeaders.HOST) +
                "\n===Headers=== User-Agent: " + request.getHeader(HttpHeaders.USER_AGENT) +
                "\n===Headers=== Parameters: " + new JSONObject(request.getParameterMap()).toString() +
                // 打印响应结果，内容过长会影响效率
                // "\n\n===Result=== " + new JSONObject(result).toString() +
                "\n================  Request End  ================\n";
        log.info(beforeLog);
        return point.proceed();
    }
}
