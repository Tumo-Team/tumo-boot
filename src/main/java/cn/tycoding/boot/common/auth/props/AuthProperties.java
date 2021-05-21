package cn.tycoding.boot.common.auth.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Auth模块 YML配置映射实体
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@ConfigurationProperties("tumo-boot.auth")
public class AuthProperties {

    private List<String> skipUrl = new ArrayList();
}
