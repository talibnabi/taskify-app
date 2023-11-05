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


    /**
     * Get user information by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity with the requested user response.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserResponseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserResponseById(id));
    }


    /**
     * Get user information by their username.
     *
     * @param username The username of the user to retrieve.
     * @return ResponseEntity with the requested user response.
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserResponseByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserResponseByUsername(username));
    }


    /**
     * Get user information by their email.
     *
     * @param email The email address of the user to retrieve.
     * @return ResponseEntity with the requested user response.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserResponseByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getUserResponseByEmail(email));
    }


    /**
     * Get a list of users associated with a specific organization by its ID.
     *
     * @param id The ID of the organization to retrieve users for.
     * @return ResponseEntity with a list of user responses.
     */
    @GetMapping("/organization/id/{id}")
    public ResponseEntity<List<UserResponse>> getAllUserResponseByOrganizationId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserResponseByOrganizationId(id));
    }

}
