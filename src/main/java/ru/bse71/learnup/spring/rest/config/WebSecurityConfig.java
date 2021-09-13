package ru.bse71.learnup.spring.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by bse71
 * Date: 13.09.2021
 * Time: 2:32
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        PasswordEncoder passwordEncoder = passwordEncoder();

        UserDetails user = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_USER"));
            }

            @Override
            public String getPassword() {
                return passwordEncoder.encode("user123");
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

        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .and()

                .userDetailsService(username -> {
                    if (user.getUsername().equals(username)) return user;
                    throw new UsernameNotFoundException("Нет такого пользователя");
                });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
