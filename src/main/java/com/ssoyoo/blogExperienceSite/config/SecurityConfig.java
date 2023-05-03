package com.ssoyoo.blogExperienceSite.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig  {


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*스프링 시큐리티의 세부 설정은 SecurityFilterChain 빈을 생성하여 설정 가능*/

        http.authorizeRequests().antMatchers("/**").permitAll();
        /* 모든 인증되지 않은 요청을 허락한다는 의미 - 로그인하지않더라도 모든페이지에 접근가능*/

        return http.build();
    }
    

    }