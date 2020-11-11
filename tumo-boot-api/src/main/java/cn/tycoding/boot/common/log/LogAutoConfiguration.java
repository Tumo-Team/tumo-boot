package cn.tycoding.boot.common.log;

import cn.tycoding.boot.common.log.aspect.ApiLogAspect;
import cn.tycoding.boot.common.log.event.LogListener;
import cn.tycoding.boot.modules.setting.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author tycoding
 * @since 2020/10/31
 */
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    @Bean
    public LogListener logListener(LogService logService) {
        return new LogListener(logService);
    }

    @Bean
    public ApiLogAspect logAspect() {
        return new ApiLogAspect();
    }
}