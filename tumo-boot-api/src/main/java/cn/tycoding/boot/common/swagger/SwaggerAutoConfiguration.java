package cn.tycoding.boot.common.swagger;

import cn.tycoding.boot.common.swagger.props.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@Order
@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerAutoConfiguration {

}
