package com.sau.dims.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private final JwtGenerator tokenService;
    private final CustomUserDetailsService userDetailsService;

    public AuthFilter(JwtGenerator tokenService, CustomUserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    final String header = request.getHeader("Authorization");
    final String username;
    final String token;
    System.out.printf("header: %s\n",header);
    if(header == null || !header.startsWith("Bearer ")){
        filterChain.doFilter(request,response);
        return;
    }
    token = header.substring(7);
    username = tokenService.extractUsername(token);
    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        UserDetails user = this.userDetailsService.loadUserByUsername(username);
        if (tokenService.isValid(token,username)){
            System.out.printf("User token is valid: %s\n",username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            System.out.printf("role: %s\n",authenticationToken.getAuthorities().toString());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
    filterChain.doFilter(request,response);
    }
}
