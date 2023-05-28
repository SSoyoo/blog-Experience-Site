package com.ssoyoo.blogExperienceSite.config;

import com.ssoyoo.blogExperienceSite.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig   {

    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
    

        httpSecurity.cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/user/sign-up", "/user/sign-in").permitAll()
                .antMatchers(HttpMethod.GET,"users/me").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/users/update").hasRole("USER")
                .antMatchers("/admin/sign-up", "/admin/sign-in").permitAll()
                .antMatchers(HttpMethod.GET, "/campaign/**", "/search", "/review/**").permitAll()
                .antMatchers(HttpMethod.POST, "/campaign" ).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/campaign/**", "/review/**").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/campaign/**", "/review/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
                    

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    
    
    
    }


    
    

    }