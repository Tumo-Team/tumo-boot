package cn.tycoding.boot.common.auth;

import cn.tycoding.boot.common.auth.props.AuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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

}
