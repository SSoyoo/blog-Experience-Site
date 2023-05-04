package com.ssoyoo.blogExperienceSite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        //TODO: 모든 경로 다 허용해 뒀고, 기능별로 어떻게 다르게 하는지 공부해서 변경필요! 
        httpSecurity.cors().and()
                    .csrf().disable()
                    .httpBasic().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests().antMatchers("/**").permitAll();
                    

        httpSecurity.addFilterBefore(jwtAuthnticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    
    
    
    }


    
    

    }