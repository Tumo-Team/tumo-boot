package cn.tycoding.boot.common.mybatis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义Mybatis模块 YML配置映射实体
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@ConfigurationProperties("tumo-boot.mybatis")
public class MybatisProperties {

    /**
     * 是否打印Mybatis SQL日志
     */
    private Boolean enable;
}
