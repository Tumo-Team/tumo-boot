package cn.tycoding.boot.common.log.aspect;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.tycoding.boot.common.auth.utils.SecurityUtil;
import cn.tycoding.boot.common.auth.utils.SpringContextHolder;
import cn.tycoding.boot.common.log.annotation.ApiLog;
import cn.tycoding.boot.common.log.event.LogEvent;
import cn.tycoding.boot.modules.setting.entity.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @author tycoding
 * @since 2020/10/31
 */
@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    @Around("@annotation(apiLog)")
    public Object around(ProceedingJoinPoint point, ApiLog apiLog) throws JsonProcessingException {
        try {
            String className = point.getTarget().getClass().getName();
            String methodName = point.getSignature().getName();

            long beginTime = System.currentTimeMillis();
            Object result = point.proceed();
            long time = System.currentTimeMillis() - beginTime;

            HttpServletRequest request = ((ServletRequestAttributes)
                    Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            Log log = new Log()
                    .setUsername(SecurityUtil.getUsername())
                    .setOperation(apiLog.value())
                    .setCreateTime(new Date())
                    .setIp(ServletUtil.getClientIP(request))
                    .setUrl(URLUtil.getPath(request.getRequestURI()))
                    .setMethod(className + "." + methodName + "()")
                    .setParams(HttpUtil.toParams(request.getParameterMap()))
                    .setUserAgent(request.getHeader("user-agent"))
                    .setTime(time);

            SpringContextHolder.publishEvent(new LogEvent(log));
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
