package cn.tycoding.boot.common.log.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义Log模块 YML配置映射实体
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@ConfigurationProperties("tumo-boot.log")
public class LogProperties {

    /**
     * 是否打印API请求日志
     */
    private Boolean enable;
}
