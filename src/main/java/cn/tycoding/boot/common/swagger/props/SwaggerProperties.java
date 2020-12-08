package cn.tycoding.boot.common.swagger.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger配置
 *
 * @author tycoding
 * @since 2020/10/15
 */
@Data
@ConfigurationProperties("tumo-boot.swagger")
public class SwaggerProperties {

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * URL
     */
    private String url;

    /**
     * Email
     */
    private String email;

    /**
     * 文档版本
     */
    private String version;
}
