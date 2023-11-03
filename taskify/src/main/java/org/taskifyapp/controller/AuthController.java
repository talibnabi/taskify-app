package org.taskifyapp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskifyapp.model.dto.request.AdminRegistrationRequest;
import org.taskifyapp.model.dto.request.AuthenticationRequest;
import org.taskifyapp.model.dto.response.AuthAndRegisterResponse;
import org.taskifyapp.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthAndRegisterResponse> register(@Valid @RequestBody AdminRegistrationRequest request) {
        return ResponseEntity.ok(authServiceImpl.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthAndRegisterResponse> register(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authServiceImpl.authenticateUser(request));
    }

}
