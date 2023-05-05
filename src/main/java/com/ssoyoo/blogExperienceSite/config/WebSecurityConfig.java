package com.ssoyoo.blogExperienceSite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssoyoo.blogExperienceSite.filter.JwtAuthnticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig  {

    private JwtAuthnticationFilter jwtAuthnticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthnticationFilter jwtAuthnticationFilter){
        this.jwtAuthnticationFilter = jwtAuthnticationFilter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
    

        httpSecurity.cors().and()
                    .csrf().disable()
                    .httpBasic().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers( "/users/sign-up","/users/sign-in").permitAll()
                    .antMatchers("/admin/sign-up","/admin/sign-in").permitAll()
                    .antMatchers(HttpMethod.GET, "/campaign", "/serch", "/reviews").permitAll()
                    .antMatchers(HttpMethod.POST, "/campaign", "/reviews").hasRole("USER")
                    .antMatchers(HttpMethod.DELETE, "/campaign", "/reviews").hasRole("USER")
                    .antMatchers(HttpMethod.PATCH, "/campaign", "/reviews").hasRole("USER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
                    

        httpSecurity.addFilterBefore(jwtAuthnticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    
    
    
    }


    
    

    }