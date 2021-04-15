package cn.tycoding.boot.common.swagger.config;

import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.swagger.props.SwaggerProperties;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
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
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityContext> securityContexts() {
        List<AuthorizationScope> scopes = new ArrayList<>();
        scopes.add(new AuthorizationScope("read", "read  resources"));
        scopes.add(new AuthorizationScope("write", "write resources"));
        scopes.add(new AuthorizationScope("reads", "read all resources"));
        scopes.add(new AuthorizationScope("writes", "write all resources"));

        SecurityReference securityReference = new SecurityReference("oauth2", scopes.toArray(new AuthorizationScope[]{}));
        SecurityContext securityContext = new SecurityContext(Lists.newArrayList(securityReference), PathSelectors.ant("/api/**"), null, null);
        return Lists.newArrayList(securityContext);
    }

    private ArrayList<SecurityScheme> securitySchemes() {
        List<GrantType> grantTypes = new ArrayList<>();
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(ApiConstant.API_OAUTH_TOKEN);
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
        OAuth oAuth = new OAuthBuilder().name("oauth2").grantTypes(grantTypes).build();
        return Lists.newArrayList(oAuth);
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

}
