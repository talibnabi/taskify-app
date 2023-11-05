package org.taskifyapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taskifyapp.model.dto.response.UserResponse;
import org.taskifyapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserResponseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserResponseById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserResponseByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserResponseByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserResponseByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getUserResponseByEmail(email));
    }

    @GetMapping("/organization/id/{id}")
    public ResponseEntity<List<UserResponse>> getAllUserResponseByOrganizationId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserResponseByOrganizationId(id));
    }

}
