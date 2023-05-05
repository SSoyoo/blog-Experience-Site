package com.ssoyoo.blogExperienceSite.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssoyoo.blogExperienceSite.provider.JwtTokenProvider;

@Component
public class JwtAuthnticationFilter extends OncePerRequestFilter  {

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtAuthnticationFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    
                    String jwt = parseToken(request);
                    boolean hasJwt = jwt != null;
                    if(!hasJwt){

                        filterChain.doFilter(request, response);
                        return;

                    }

                    String subject = jwtTokenProvider.validateJwt(jwt);
                    AbstractAuthenticationToken authenticationToken = 
                    new UsernamePasswordAuthenticationToken(subject, null, AuthorityUtils.NO_AUTHORITIES);

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authenticationToken);

                SecurityContextHolder.setContext(securityContext);


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
    }

    private String parseToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        boolean hasToken = 
            token != null &&
            !token.equalsIgnoreCase("null");

        if(!hasToken) return null;

        String jwt = token.substring(7);
        return jwt;
    }
    
}
