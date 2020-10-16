package cn.tycoding.boot.common.props;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "tumo-boot")
public class TumoProperties {

    private SwaggerProperties swagger = new SwaggerProperties();
}
