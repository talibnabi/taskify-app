package org.taskifyapp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.service.AdminService;

@RestController
@RequestMapping("/api/for-admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/user-create")
    public void createUser(@Valid @RequestBody RegistrationRequest request) {
        adminService.createUser(request);
    }
}
