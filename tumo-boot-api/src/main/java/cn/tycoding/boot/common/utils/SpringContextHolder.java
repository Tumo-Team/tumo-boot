package cn.tycoding.boot.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2020/10/31
 */
@Service
@Lazy(value = false)
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext == null) {
            return;
        }
        applicationContext.publishEvent(event);
    }
}
