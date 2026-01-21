package com.mindvault.online_service.security;

<<<<<<< HEAD
import com.mindvault.online_service.entities.User;

import com.mindvault.online_service.repositories.UserRepository;
=======
import com.mindvault.online_service.service.CustomUserDetailsService;
>>>>>>> development
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        Claims claims = jwtService.extractClaims(token);
        String email = claims.getSubject();

        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

<<<<<<< HEAD
        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Putting the actual 'user' entity into the principal
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
=======
        	UserDetails userDetails =
        	        userDetailsService.loadUserByUsername(email);

        	UsernamePasswordAuthenticationToken auth =
        	        new UsernamePasswordAuthenticationToken(
        	                userDetails,
        	                null,
        	                userDetails.getAuthorities()
        	        );
>>>>>>> development

        	SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}