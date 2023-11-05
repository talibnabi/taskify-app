package org.taskifyapp.controller;

import lombok.RequiredArgsConstructor;
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
    public UserResponse getUserResponseById(@PathVariable("id") Long id) {
        return userService.getUserResponseById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse getUserResponseByUsername(@PathVariable("username") String username) {
        return userService.getUserResponseByUsername(username);
    }

    @GetMapping("/email/{email}")
    public UserResponse getUserResponseByEmail(@PathVariable("email") String email) {
        return userService.getUserResponseByEmail(email);
    }

    @GetMapping("/organization/id/{id}")
    public List<UserResponse> getAllUserResponseByOrganizationId(@PathVariable("id") Long id) {
        return userService.getUserResponseByOrganizationId(id);
    }

}
