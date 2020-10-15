package cn.tycoding.boot.modules.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author tycoding
 * @since 2020/10/15
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //允许使用iframe嵌套，避免swagger-ui不被加载的问题
                .headers()
                .frameOptions().disable()

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs/**")
                .permitAll()

                .antMatchers("/actuator/**", "/user/info/*")
                .permitAll()

                .anyRequest()
                .authenticated()

                .and()
                .csrf().disable();
    }
}
