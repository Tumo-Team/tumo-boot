package cn.tycoding.boot.common.swagger;

import cn.tycoding.boot.common.swagger.props.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Swagger配置注入
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Order
@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerAutoConfiguration {

}
