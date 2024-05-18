package com.sau.dims.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtGenerator {

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();

        String role = authentication.getAuthorities().toString();

        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);


        return Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.JWT_SECRET)
                .compact();
    }

}
