package com.ssoyoo.blogExperienceSite.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssoyoo.blogExperienceSite.service.implement.AdminDetailsServiceImpl;
import com.ssoyoo.blogExperienceSite.service.implement.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.GrantedAuthority;

import com.ssoyoo.blogExperienceSite.provider.JwtTokenProvider;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter  {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final AdminDetailsServiceImpl adminDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseToken(request);
            boolean hasJwt = jwt != null;

            if (hasJwt) {
                String subject = jwtTokenProvider.validateJwt(jwt);
                List<GrantedAuthority> authorities = jwtTokenProvider.getAuthoritiesFromJwt(jwt);
                UserDetails userDetails;
                if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                    userDetails = adminDetailsService.loadUserByUsername(subject);
                } else {
                    userDetails = userDetailsService.loadUserByUsername(subject);
                }
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

    private String parseToken(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        boolean hasToken = token != null && token.startsWith("Bearer ");
        if (!hasToken) {
            return null;
        }
        return token.substring(7);
    }

    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

}
