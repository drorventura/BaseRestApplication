package com.dror.config;

import com.dror.extensions.InMemoryUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * User: Dror
 * Date: 1/8/2016
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/api/**").authenticated()
                .antMatchers("/**").denyAll()
            .and()
                .httpBasic()
            .and()
                .formLogin()
                .defaultSuccessUrl("/api/test/version")
                .permitAll()
            .and()
                .logout()
                .permitAll()
            .and()
                .csrf().disable()
        ;
    }

    @Bean
    public UserDetailsService userService()
    {
        return new InMemoryUserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new StandardPasswordEncoder();
    }
}
