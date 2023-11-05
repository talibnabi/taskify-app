package org.taskifyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.taskifyapp.exception.custom.OrganizationNotFoundException;
import org.taskifyapp.exception.custom.DuplicateException;
import org.taskifyapp.exception.custom.PasswordNotMatchedException;
import org.taskifyapp.exception.custom.UserNotFoundException;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.model.entity.Organization;
import org.taskifyapp.model.entity.User;
import org.taskifyapp.model.enums.UserRole;
import org.taskifyapp.repository.UserRepository;
import org.taskifyapp.service.AdminService;
import org.taskifyapp.service.OrganizationService;
import org.taskifyapp.service.UserCheckingFieldService;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService, UserCheckingFieldService {
    private final UserRepository userRepository;
    private final OrganizationService organizationService;
    private final ModelMapper modelMapper;

    @Override
    public void createUser(RegistrationRequest registerRequest) {
        passwordMatchingChecking(registerRequest);
        userUsernameDuplicatingChecking(registerRequest);
        userEmailDuplicatingChecking(registerRequest);
        String adminUsername = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        System.out.println(adminUsername);
        User admin = userRepository.findByEmail(adminUsername).orElseThrow(() -> new UserNotFoundException("user not found"));
        User user = modelMapper.map(registerRequest, User.class);
        user.setUserRole(UserRole.USER);
        user.setOrganization(admin.getOrganization());
        user.setOrganization(admin.getOrganization());
        userRepository.save(user);
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
