package ru.bse71.learnup.spring.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import ru.bse71.learnup.spring.rest.security.MyPermissionEvaluator;

/**
 * Created by bse71
 * Date: 07.09.2021
 * Time: 19:06
 */

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Bean
    public DefaultMethodSecurityExpressionHandler expressionHandler() {
        final DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(
                new MyPermissionEvaluator());
        return expressionHandler;
    }

}
