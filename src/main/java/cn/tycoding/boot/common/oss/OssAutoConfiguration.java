package cn.tycoding.boot.common.oss;

import cn.tycoding.boot.common.oss.props.LocalFileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Oss配置注入
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Order
@Configuration
@EnableConfigurationProperties({LocalFileProperties.class})
public class OssAutoConfiguration {

}
