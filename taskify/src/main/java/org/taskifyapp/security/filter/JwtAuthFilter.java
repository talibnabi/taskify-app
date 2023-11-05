package org.taskifyapp.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.taskifyapp.security.service.impl.JwtServiceImpl;

import java.io.IOException;
import java.util.Set;

import static org.taskifyapp.util.JwtAuthFilterUtil.AUTH_HEADER;
import static org.taskifyapp.util.JwtAuthFilterUtil.BEARER;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtServiceImpl;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTH_HEADER);
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtServiceImpl.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtServiceImpl.isTokenValid(jwt, userDetails(userEmail))) {
                UsernamePasswordAuthenticationToken authToken = usernamePasswordAuthenticationToken(jwt, userEmail);
                authToken.setDetails(authenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails userDetails(String userMail) {
        return this.userDetailsService.loadUserByUsername(userMail);
    }

    private WebAuthenticationDetailsSource authenticationDetailsSource() {
        return new WebAuthenticationDetailsSource();
    }

    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(String token, String subject) {
        return new UsernamePasswordAuthenticationToken(
                userPrincipal(token, subject), "",
                simpleGrantedAuthoritySet(token)
        );
    }

    private Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet(String token) {
        return jwtServiceImpl.simpleGrantedAuthorities(token);
    }

    private User userPrincipal(String token, String subject) {
        return new User(subject, "", simpleGrantedAuthoritySet(token));
    }

}
