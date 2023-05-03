package com.ssoyoo.blogExperienceSite.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssoyoo.blogExperienceSite.provider.JwtTokenProvider;

@Component
public class jwtAuthnticationFilter extends OncePerRequestFilter  {

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public jwtAuthnticationFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    }

    private String parseToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        boolean hasToken = 
            token != null &&
            token.equalsIgnoreCase("null");

        if(!hasToken) return null;

        String jwt = token.substring(7);
        return jwt;
    }
    
}
