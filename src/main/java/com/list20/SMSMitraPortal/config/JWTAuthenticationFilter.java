package com.list20.SMSMitraPortal.config;


import java.io.IOException;

import com.list20.SMSMitraPortal.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private final UserService userService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authHeader) && StringUtils.startsWith(authHeader, "Bearer")) {
            String jwt = authHeader.substring(7);
            String userName = this.userService.extractUserName(jwt);
            if (StringUtils.isNoneEmpty(new CharSequence[]{userName}) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.getUserDetailsService().loadUserByUsername(userName);
                if (this.userService.isTokenValid(jwt, userDetails)) {
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, (Object)null, userDetails.getAuthorities());
                    token.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            }

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    UserDetailsService getUserDetailsService() {
        return this.userService.userDetailsService();
    }

    public JWTAuthenticationFilter(final UserService userService) {
        this.userService = userService;
    }
}

