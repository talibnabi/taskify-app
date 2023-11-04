package org.taskifyapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskifyapp.model.dto.request.OrganizationRegistrationRequest;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.model.dto.request.AuthenticationRequest;
import org.taskifyapp.model.dto.response.AuthAndRegisterResponse;
import org.taskifyapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthAndRegisterResponse> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthAndRegisterResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        authService.authenticateUser(request);
        return ResponseEntity.ok(authService.authenticateUser(request));
    }

    @PostMapping("/register-organization")
    public ResponseEntity<String> registerOrganization(@Valid @RequestBody OrganizationRegistrationRequest request) {
        authService.registerOrganization(request);
        return ResponseEntity.ok("Registration successfully! ");
    }

}
