package org.taskifyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.taskifyapp.exception.custom.DuplicateException;
import org.taskifyapp.exception.custom.PasswordNotMatchedException;
import org.taskifyapp.exception.custom.UserNotFoundException;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.model.entity.User;
import org.taskifyapp.model.enums.UserRole;
import org.taskifyapp.repository.UserRepository;
import org.taskifyapp.service.AdminService;
import org.taskifyapp.service.UserCheckingFieldService;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService, UserCheckingFieldService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    /**
     * Creates a new user based on the provided registration request.
     * Performs password matching checking, username duplicating checking, and email duplicating checking.
     * The user is created, assigned the USER role, and associated with the same organization as the admin.
     *
     * @param registerRequest The registration request containing user information.
     */
    @Override
    public void createUser(RegistrationRequest registerRequest) {
        passwordMatchingChecking(registerRequest);
        userUsernameDuplicatingChecking(registerRequest);
        userEmailDuplicatingChecking(registerRequest);
        String adminUsername = getAdminUsernameFromSecurityContextHolder();
        User userAdmin = getUser(adminUsername);
        User user = modelMapper.map(registerRequest, User.class);
        user.setUserRole(UserRole.USER);
        user.setOrganization(userAdmin.getOrganization());
        userRepository.save(user);
    }

    private User getUser(String adminUsername) {
        return userRepository.findByEmail(adminUsername).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private String getAdminUsernameFromSecurityContextHolder() {
        return ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    @Override
    public void passwordMatchingChecking(RegistrationRequest registerRequest) {
        if (!registerRequest.isPasswordsMatched()) {
            throw new PasswordNotMatchedException("User password not matched.");
        }
    }

    @Override
    public void userEmailDuplicatingChecking(RegistrationRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new DuplicateException("You cannot create a new user with the same email.");
        }
    }

    @Override
    public void userUsernameDuplicatingChecking(RegistrationRequest registerRequest) {
        if (userRepository.findUserByUsername(registerRequest.getUsername()).isPresent()) {
            throw new DuplicateException("You cannot create a new user with the same username.");
        }
    }


}
