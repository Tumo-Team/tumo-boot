package cn.tycoding.boot.common.swagger.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.tycoding.boot.common.auth.constant.ApiConstant;
import cn.tycoding.boot.common.swagger.props.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Configuration
@EnableKnife4j
@EnableSwagger2WebMvc
@RequiredArgsConstructor
public class SwaggerConfig {

    private final SwaggerProperties swagger;

    @Bean
    public Docket authDocket() {
        return docket("授权模块", swagger.getBasePackage() + ".modules.auth.endpoint");
    }

    @Bean
    public Docket upmsDocket() {
        return docket("系统模块", swagger.getBasePackage() + ".modules.upms.controller");
    }

    @Bean
    public Docket ossDocket() {
        return docket("资源模块", swagger.getBasePackage() + ".modules.resource");
    }

    private Docket docket(String groupName, String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo(swagger))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityContext> securityContexts() {
        List<AuthorizationScope> scopes = new ArrayList<>();
        swagger.getAuthorizationScopeList().forEach(s -> {
            scopes.add(new AuthorizationScope(s.getScope(), s.getDescription()));
        });

        SecurityReference securityReference = new SecurityReference("oauth2", scopes.toArray(new AuthorizationScope[]{}));
        return CollectionUtil.newArrayList(SecurityContext
                .builder()
                .securityReferences(CollectionUtil.newArrayList(securityReference))
                .build()
        );
    }

    private ArrayList<SecurityScheme> securitySchemes() {
        List<GrantType> grantTypes = new ArrayList<>();
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(ApiConstant.API_OAUTH_TOKEN);
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
        OAuth oAuth = new OAuthBuilder().name("oauth2").grantTypes(grantTypes).build();
        return CollectionUtil.newArrayList(oAuth);
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
