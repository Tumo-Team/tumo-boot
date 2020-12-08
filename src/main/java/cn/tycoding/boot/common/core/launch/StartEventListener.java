package cn.tycoding.boot.common.core.launch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

/**
 * @author tycoding
 * @since 2020/10/9
 */
@Slf4j
@Configuration
public class StartEventListener {

    public StartEventListener() {
    }

    @Async
    @EventListener({WebServerInitializedEvent.class})
    public void afterStart(WebServerInitializedEvent event) {
        Environment environment = event.getApplicationContext().getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        int port = event.getWebServer().getPort();
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.info("----[{}]----启动完成。当前使用端口：[{}]，环境变量：[{}]", appName, port, profile);
    }
}
