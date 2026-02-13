package com.yugmehta.auth_service.filter;

import com.yugmehta.auth_service.service.JwtService;
import com.yugmehta.auth_service.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public JwtFilter(JwtService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1️⃣ Get Authorization header
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Extract JWT
        String jwt = authHeader.substring(7);

        // 3️⃣ Extract username from token
        String username = jwtService.extractUsername(jwt);

        // 4️⃣ If username exists & user not already authenticated
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userService.loadUserByUsername(username);

            // 5️⃣ Validate token
            if (jwtService.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 6️⃣ Set authentication
                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
        }

        // 7️⃣ Continue filter chain
        filterChain.doFilter(request, response);
    }
}
