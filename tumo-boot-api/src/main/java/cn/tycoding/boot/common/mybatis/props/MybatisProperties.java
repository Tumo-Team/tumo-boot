package cn.tycoding.boot.common.mybatis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger配置
 *
 * @author tycoding
 * @since 2020/10/15
 */
@Data
@ConfigurationProperties("tumo-boot.mybatis")
public class MybatisProperties {

    /**
     * 是否打印Mybatis SQL日志
     */
    private Boolean enable;
}
