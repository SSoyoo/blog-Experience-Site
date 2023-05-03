package com.ssoyoo.blogExperienceSite.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    public String createJwt(String subject) {

        Date expriedDate = Date.from((Instant.now()).plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setSubject(subject).setIssuedAt(new Date()).setExpiration(expriedDate)
                .compact();

        return jwt;

    }

    public String validateJwt(String jwt) {
        Claims claims = 
            Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt).getBody();

        return claims.getSubject();

    }

}
