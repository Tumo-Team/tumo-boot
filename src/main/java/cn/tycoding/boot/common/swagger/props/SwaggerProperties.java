package cn.tycoding.boot.common.swagger.props;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 自定义Swagger模块 YML配置映射实体
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Data
@ConfigurationProperties("tumo-boot.swagger")
public class SwaggerProperties {

    /**
     * Base package扫描地址
     */
    private String basePackage;

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

    /**
     * Scope
     */
    private List<AuthorizationScope> authorizationScopeList;

    @Data
    @NoArgsConstructor
    public static class AuthorizationScope {
        private String scope = "";
        private String description = "";
    }
}
