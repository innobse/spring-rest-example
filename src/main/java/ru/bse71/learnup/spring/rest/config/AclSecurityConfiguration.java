package ru.bse71.learnup.spring.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bse71
 * Date: 14.09.2021
 * Time: 3:11
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AclSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return defaultMethodSecurityExpressionHandler;
    }

    @Bean
    public MethodSecurityExpressionHandler
    defaultMethodSecurityExpressionHandler(JdbcMutableAclService aclService) {
        DefaultMethodSecurityExpressionHandler expressionHandler
                = new DefaultMethodSecurityExpressionHandler();
        AclPermissionEvaluator permissionEvaluator
                = new AclPermissionEvaluator(aclService);
        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        return expressionHandler;
    }

    @Bean
    public JdbcMutableAclService aclService(DataSource dataSource, LookupStrategy lookupStrategy, EhCacheBasedAclCache aclCache) {
        return new JdbcMutableAclService(
                dataSource, lookupStrategy, aclCache);
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(
                new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(
                new ConsoleAuditLogger());
    }

    @Bean
    public EhCacheBasedAclCache aclCache(
            EhCacheFactoryBean cacheFactoryBean,
            PermissionGrantingStrategy permissionGrantingStrategy,
            AclAuthorizationStrategy aclAuthorizationStrategy) {
        return new EhCacheBasedAclCache(
                cacheFactoryBean.getObject(),
                permissionGrantingStrategy,
                aclAuthorizationStrategy);
    }

    @Bean
    public EhCacheFactoryBean aclEhCacheFactoryBean(EhCacheManagerFactoryBean cacheManager) {
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(cacheManager.getObject());
        ehCacheFactoryBean.setCacheName("aclCache");
        return ehCacheFactoryBean;
    }

    @Bean
    public EhCacheManagerFactoryBean aclCacheManager() {
        return new EhCacheManagerFactoryBean();
    }

    @Bean
    public LookupStrategy lookupStrategy(DataSource dataSource, EhCacheBasedAclCache cache, AclAuthorizationStrategy strategy, PermissionGrantingStrategy permissionGrantingStrategy) {
        return new BasicLookupStrategy(
                dataSource,
                cache,
                strategy,
                permissionGrantingStrategy);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        return username -> {
            if (username.equals("user")) return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    return authorities;
                }

                @Override
                public String getPassword() {
                    return encoder.encode("user123");
                }

                @Override
                public String getUsername() {
                    return "user";
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
            throw new UsernameNotFoundException("Вот так вот");
        };
    }
}
