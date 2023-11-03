package org.taskifyapp.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.taskifyapp.model.dto.request.AdminRegistrationRequest;
import org.taskifyapp.model.dto.request.AuthenticationRequest;
import org.taskifyapp.model.dto.response.AuthAndRegisterResponse;
import org.taskifyapp.model.entity.User;
import org.taskifyapp.model.enums.UserRole;
import org.taskifyapp.repository.UserRepository;
import org.taskifyapp.security.JwtService;
import org.taskifyapp.service.AuthService;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthAndRegisterResponse registerUser(AdminRegistrationRequest request) {
        User user = getBuildedUser(request);
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return getAuthAndRegisterResponse(jwt);
    }


    @Override
    public AuthAndRegisterResponse authenticateUser(AuthenticationRequest request) {
        getAuthentication(request);
        User user = findByEmail(request);
        String jwt = jwtService.generateToken(user);
        return getAuthAndRegisterResponse(jwt);
    }


    private User getBuildedUser(AdminRegistrationRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.fromId(1))
                .build();
    }

    private User findByEmail(AuthenticationRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    private AuthAndRegisterResponse getAuthAndRegisterResponse(String jwt) {
        return AuthAndRegisterResponse.builder()
                .token(jwt)
                .build();
    }


    private void getAuthentication(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    }

}
