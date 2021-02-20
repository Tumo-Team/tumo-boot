package cn.tycoding.boot.common.log.aspect;

import cn.tycoding.boot.common.auth.utils.SpringContextHolder;
import cn.tycoding.boot.common.log.event.LogEvent;
import cn.tycoding.boot.common.log.utils.SysLogUtil;
import cn.tycoding.boot.modules.system.entity.SysLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2020/10/31
 */
@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    @Around("@annotation(apiLog)")
    public Object around(ProceedingJoinPoint point, cn.tycoding.boot.common.log.annotation.ApiLog apiLog) throws JsonProcessingException {
        try {
            String className = point.getTarget().getClass().getName();
            String methodName = point.getSignature().getName();

            long beginTime = System.currentTimeMillis();
            Object result = point.proceed();
            long time = System.currentTimeMillis() - beginTime;

            String method = className + "." + methodName + "()";
            SysLog sysLog = SysLogUtil.build(1, apiLog.value(), method, time);

            SpringContextHolder.publishEvent(new LogEvent(sysLog));
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
