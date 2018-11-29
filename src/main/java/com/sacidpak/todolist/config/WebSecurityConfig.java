package com.sacidpak.todolist.config;

import com.sacidpak.todolist.provider.AuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author sacidpak
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.sacidpak.todolist.user")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthenticationProviderImpl authenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.authenticationProvider(authenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login*").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome", true)
                .and()
                .logout().logoutSuccessUrl("/login");

    }


}