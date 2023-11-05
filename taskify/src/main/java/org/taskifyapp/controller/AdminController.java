package org.taskifyapp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.service.AdminService;

@RestController
@RequestMapping("/api/for-admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    /**
     * Create a new user.
     *
     * @param request The user registration request data.
     * @return ResponseEntity with a success message upon user creation.
     */
    @PostMapping("/user-create")
    public ResponseEntity<String> createUser(@Valid @RequestBody RegistrationRequest request) {
        adminService.createUser(request);
        return ResponseEntity.ok("User created successfully!");
    }

}
