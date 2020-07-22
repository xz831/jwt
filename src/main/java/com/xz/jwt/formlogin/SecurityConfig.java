package com.xz.jwt.formlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Package: com.xz.jwt.formlogin
 * @ClassName: SecurityConfig
 * @Author: xz
 * @Date: 2020/7/22 17:10
 * @Version: 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
                .failureUrl("/login.html")
                .and()
            .authorizeRequests()
                .antMatchers("/login.html","/login")
                .permitAll()
                .antMatchers("/order")
                .hasAnyAuthority("ROLE_user","ROLE_admin")
                .antMatchers("/system/user","/system/role","system/menu")
                .hasAnyRole("admin")
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.logout().logoutUrl("/logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(cryptPasswordEncoder.encode("123456"))
                .roles("user")
                .and()
                .withUser("admin")
                .password(cryptPasswordEncoder.encode("123456"))
                .roles("admin")
                .and()
                .passwordEncoder(cryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){return new BCryptPasswordEncoder();}
}
