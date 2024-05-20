package com.sau.dims.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


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
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpired(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public boolean isValid(String token, String username){
        return (extractUsername(token).equals(username) && !isExpired(token));
    }
    private boolean isExpired(String token){
        return (extractExpired(token).before(new Date(System.currentTimeMillis())));
    }
    private <T> T extractClaim(String token, Function<Claims,T> function){
        final  Claims claims = extractClaims(token);
        return function.apply(claims);
    }
    private Claims extractClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getKey(){
        byte[] bytes = Decoders.BASE64.decode(SecurityConstants.JWT_SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }

}
