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

    /**
     * Register a new user.
     *
     * @param request The registration request data.
     * @return ResponseEntity with a success message upon user registration.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok("Registration successfully! ");
    }


    /**
     * Authenticate and log in a user.
     *
     * @param request The authentication request data.
     * @return ResponseEntity with authentication and registration response.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthAndRegisterResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        authService.authenticateUser(request);
        return ResponseEntity.ok(authService.authenticateUser(request));
    }


    /**
     * Register a new organization.
     *
     * @param request The organization registration request data.
     * @return ResponseEntity with a success message upon organization registration.
     */
    @PostMapping("/register-organization")
    public ResponseEntity<String> registerOrganization(@Valid @RequestBody OrganizationRegistrationRequest request) {
        authService.registerOrganization(request);
        return ResponseEntity.ok("Registration successfully! ");
    }

}
