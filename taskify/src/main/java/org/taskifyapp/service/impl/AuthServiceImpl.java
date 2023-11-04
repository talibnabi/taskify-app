package org.taskifyapp.service.impl;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.taskifyapp.exception.custom.DuplicateException;
import org.taskifyapp.exception.custom.UserNotFoundException;
import org.taskifyapp.exception.custom.PasswordNotMatchedException;
import org.taskifyapp.model.dto.request.OrganizationRegistrationRequest;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.model.dto.request.AuthenticationRequest;
import org.taskifyapp.model.dto.response.AuthAndRegisterResponse;
import org.taskifyapp.model.entity.Organization;
import org.taskifyapp.model.entity.User;
import org.taskifyapp.model.enums.UserRole;
import org.taskifyapp.repository.UserRepository;
import org.taskifyapp.security.JwtService;
import org.taskifyapp.service.*;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserCheckingFieldService,
        OrganizationCheckingFieldService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final OrganizationService organizationService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    /*REGISTER ADMIN*/
    @Override
    public AuthAndRegisterResponse registerUser(RegistrationRequest request) {
        passwordMatchingChecking(request);
        userUsernameDuplicatingChecking(request);
        userEmailDuplicatingChecking(request);
        User user = getBuildedUser(request);
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return getAuthAndRegisterResponse(jwt);
    }


    /*LOGIN ADMIN*/
    @Override
    public AuthAndRegisterResponse authenticateUser(AuthenticationRequest request) {
        getAuthentication(request);
        User user = findByEmail(request);
        String jwt = jwtService.generateToken(user);
        return getAuthAndRegisterResponse(jwt);
    }

    /*REGISTER ORGANIZATION*/
    @Override
    public void registerOrganization(OrganizationRegistrationRequest request) {
        userByUsernameFromOrganization(request);
        organizationNameDuplicatingChecking(request);
        Organization organization = modelMapper.map(request, Organization.class);
        organizationService.saveOrganization(organization);
        User admin = getUserFromSecurityContextHolder();
        admin.setOrganization(organization);
        userRepository.save(admin);
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

    @Override
    public void organizationNameDuplicatingChecking(OrganizationRegistrationRequest registrationRequest) {
        if (organizationService.getOrganizationByName(registrationRequest.getOrganizationName()).isPresent()) {
            throw new DuplicateException("You cannot create a new organization with the same organization name.");
        }
    }

    private User findByEmail(AuthenticationRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }


    private void userByUsernameFromOrganization(OrganizationRegistrationRequest request) {
//        if (request.getUsername().equals(getUsernameAdmin())) {
//            userService.findUserByUsername(request.getUsername()).orElseThrow(
//                    () -> new UserNotFoundException("User not found")
//            );
//        }
        userService.findUserByUsername(request.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
    }

    public User getUserFromSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public String getUsernameFromSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return ((User) authentication.getPrincipal()).getUsername();
        } else {
            return null;
        }
    }

    private User getBuildedUser(RegistrationRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.fromId(1))
                .build();
    }

    private AuthAndRegisterResponse getAuthAndRegisterResponse(String jwt) {
        return AuthAndRegisterResponse.builder()
                .token(jwt)
                .build();
    }

    private void getAuthentication(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    }

}
