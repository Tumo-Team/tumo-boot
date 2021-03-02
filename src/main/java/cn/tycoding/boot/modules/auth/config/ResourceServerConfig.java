package cn.tycoding.boot.modules.auth.config;

import cn.tycoding.boot.common.auth.props.AuthProperties;
import cn.tycoding.boot.modules.auth.component.ResourceAuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器
 *
 * @author tycoding
 * @since 2020/10/15
 */
@Configuration
@RequiredArgsConstructor
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final String[] swagger_ignores = new String[]{"/swagger-ui.html", "/doc.html/**", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs", "/webjars/**"};

    private final AuthProperties authProperties;
    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, swagger_ignores)
                .permitAll()

                .antMatchers(authProperties.getSkipUrl().toArray(new String[0]))
                .permitAll()

                .anyRequest()
                .authenticated()

                .and()
                .csrf().disable();
    }
}
