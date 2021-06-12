package cn.tycoding.boot.common.auth;

import cn.tycoding.boot.common.auth.props.AuthProperties;
import cn.tycoding.boot.common.auth.utils.AuthService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auth 配置注入
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Configuration
@EnableConfigurationProperties({AuthProperties.class})
public class AuthAutoConfiguration {

    @Bean("auth")
    public AuthService authService() {
        return new AuthService();
    }
}
