package cn.tycoding.boot.common.swagger.config;

import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.swagger.props.SwaggerProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * Swagger 配置
 *
 * @author tycoding
 * @since 2020/10/15
 */
@Configuration
@AllArgsConstructor
public class SwaggerConfig {

    private final SwaggerProperties swagger;

    @Bean
    public Docket authDocket() {
        return docket("授权模块", CommonConstant.BASE_PACKAGE + ".modules.auth.endpoint");
    }

    @Bean
    public Docket systemDocket() {
        return docket("系统模块", CommonConstant.BASE_PACKAGE + ".modules.upms.controller");
    }

    @Bean
    public Docket settingDocket() {
        return docket("设置模块", CommonConstant.BASE_PACKAGE + ".modules.setting.controller");
    }

    private Docket docket(String groupName, String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo(swagger))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(apiKey());
    }

    private ApiInfo apiInfo(SwaggerProperties swagger) {
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .termsOfServiceUrl(swagger.getUrl())
                .contact(new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()))
                .version(swagger.getVersion())
                .build();
    }

    private List<SecurityScheme> apiKey() {
        ApiKey apiKey = new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, "header");
        return Collections.singletonList(apiKey);
    }

}
