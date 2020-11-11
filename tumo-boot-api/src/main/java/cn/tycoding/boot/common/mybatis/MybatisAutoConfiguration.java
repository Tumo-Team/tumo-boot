package cn.tycoding.boot.common.mybatis;

import cn.tycoding.boot.common.mybatis.props.MybatisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@Order
@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
public class MybatisAutoConfiguration {

}
