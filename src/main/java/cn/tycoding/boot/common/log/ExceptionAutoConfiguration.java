package cn.tycoding.boot.common.log;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * @author tycoding
 * @since 2020/11/23
 */
@Configuration
@ConditionalOnWebApplication
@AutoConfigureBefore({ErrorMvcAutoConfiguration.class})
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
public class ExceptionAutoConfiguration {

    
}
