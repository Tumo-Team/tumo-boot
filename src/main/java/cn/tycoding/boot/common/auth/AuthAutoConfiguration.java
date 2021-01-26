package cn.tycoding.boot.common.auth;

import cn.tycoding.boot.common.auth.props.AuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tycoding
 * @since 2021/1/26
 */
@Order
@Configuration
@EnableConfigurationProperties({AuthProperties.class})
public class AuthAutoConfiguration {

}
