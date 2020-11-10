package cn.tycoding.boot.common.auth.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2020/11/9
 */
@Data
@ConfigurationProperties("tumo-boot.auth")
public class AuthProperties {

    private List<String> skipUrl = new ArrayList();
}
