package cn.tycoding.boot.common.log.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger配置
 *
 * @author tycoding
 * @since 2020/10/15
 */
@Data
@ConfigurationProperties("tumo-boot.log")
public class LogProperties {

    /**
     * 是否打印API请求日志
     */
    private Boolean enable;
}
