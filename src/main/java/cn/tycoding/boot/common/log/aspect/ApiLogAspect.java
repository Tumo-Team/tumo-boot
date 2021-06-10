package cn.tycoding.boot.common.log.aspect;

import cn.tycoding.boot.common.auth.utils.SpringContextHolder;
import cn.tycoding.boot.common.log.event.LogEvent;
import cn.tycoding.boot.common.log.utils.SysLogUtil;
import cn.tycoding.boot.modules.resource.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 自定义日志记录切面
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Slf4j
@Aspect
public class ApiLogAspect {

    @Around("@annotation(apiLog)")
    public Object around(ProceedingJoinPoint point, cn.tycoding.boot.common.log.annotation.ApiLog apiLog) throws Throwable {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        long beginTime = System.currentTimeMillis();
        long time = System.currentTimeMillis() - beginTime;

        String method = className + "." + methodName + "()";
        SysLog sysLog = SysLogUtil.build(1, apiLog.value(), method, time);

        SpringContextHolder.publishEvent(new LogEvent(sysLog));
        return point.proceed();
    }

}
