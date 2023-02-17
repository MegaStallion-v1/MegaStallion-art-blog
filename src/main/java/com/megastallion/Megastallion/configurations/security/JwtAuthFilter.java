package com.megastallion.Megastallion.configurations.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


//    @Component
//
//    public class JwtAuthFilter extends OncePerRequestFilter {
//        private final UserDetailsService userDetailsService;
//        private final JwtUtils jwtUtils;
//
////        @Autowired
//        public JwtAuthFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
//            this.userDetailsService = userDetailsService;
//            this.jwtUtils = jwtUtils;
//        }
//
//        @Override
//        protected void doFilterInternal(
//                @NonNull HttpServletRequest request,
//                @NonNull HttpServletResponse response,
//                @NonNull FilterChain filterChain)throws ServletException, IOException {
//            final  String authHeader = request.getHeader(AUTHORIZATION);
//            final String userEmail;
//            final String jwtToken;
//
//            if(authHeader==null || !authHeader.startsWith("Bearer")){
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//            jwtToken = authHeader.substring(7);
//
//            userEmail = jwtUtils.extractUsername(jwtToken);
//
//            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//                final boolean isTokenValid = jwtUtils.isTokenValid(jwtToken, userDetails);
//
//                if (isTokenValid) {
//                    UsernamePasswordAuthenticationToken authToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//            filterChain.doFilter(request, response);
//        }
//    }
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtUtils jwtTokenProvider;
    private UserDetailsService userDetailsService;
    public JwtAuthFilter(JwtUtils jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // get JWT token from http request
        String token = getTokenFromRequest(request);
        // validate token
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
            // get username from token
            String username = jwtTokenProvider.getUsername(token);
            // load the user associated with token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
    private String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}

