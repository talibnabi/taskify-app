package org.taskifyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.taskifyapp.exception.OrganizationNotFoundException;
import org.taskifyapp.exception.DuplicateException;
import org.taskifyapp.exception.PasswordNotMatchedException;
import org.taskifyapp.mapper.UserMapper;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.model.entity.Organization;
import org.taskifyapp.model.entity.User;
import org.taskifyapp.service.AdminService;
import org.taskifyapp.service.OrganizationService;
import org.taskifyapp.service.UserCheckingFieldService;
import org.taskifyapp.service.UserService;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService, UserCheckingFieldService {
    private final UserService userService;
    private final OrganizationService organizationService;
    private final UserMapper userMapper;

    @Override
    public void createUser(RegistrationRequest registerRequest) {
        passwordMatchingChecking(registerRequest);
        userUsernameDuplicatingChecking(registerRequest);
        userEmailDuplicatingChecking(registerRequest);
        User admin = getUserAdmin();
        User user = userMapper.registrationRequestToUserMapper(registerRequest);
        Organization organization = getOrganizationForUser(admin);
        user.setOrganization(organization);
    }

    @Override
    public void passwordMatchingChecking(RegistrationRequest registerRequest) {
        if (!registerRequest.isPasswordsMatched()) {
            throw new PasswordNotMatchedException("User password not matched.");
        }
    }

    @Override
    public void userEmailDuplicatingChecking(RegistrationRequest registerRequest) {
        if (userService.findUserByEmail(registerRequest.getEmail()).isPresent()) {
            throw new DuplicateException("You cannot create a new user with the same email.");
        }
    }

    @Override
    public void userUsernameDuplicatingChecking(RegistrationRequest registerRequest) {
        if (userService.findUserByUsername(registerRequest.getUsername()).isPresent()) {
            throw new DuplicateException("You cannot create a new user with the same username.");
        }
    }

    private Organization getOrganizationForUser(User admin) {
        return organizationService.getOrganizationById(admin.getOrganization().getId()).stream().findFirst().orElseThrow(
                () -> new OrganizationNotFoundException("Organization not found"));
    }


    public User getUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public String getUsernameAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return ((User) authentication.getPrincipal()).getUsername();
        } else {
            return null;
        }
    }
}
