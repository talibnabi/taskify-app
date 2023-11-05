package org.taskifyapp.service.impl;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import org.taskifyapp.security.service.impl.JwtServiceImpl;
import org.taskifyapp.service.*;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserCheckingFieldService,
        OrganizationCheckingFieldService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtServiceImpl;
    private final UserService userService;
    private final OrganizationService organizationService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;


    /**
     * Registers a new user with the provided registration request data.
     * Performs password matching checking, username duplicating checking, and email duplicating checking.
     * If checks pass, the user is created, saved, and a JWT token is generated for authentication.
     *
     * @param request The registration request containing user information.
     * @return An authentication and registration response containing a JWT token upon successful registration.
     */
    @Override
    public AuthAndRegisterResponse registerUser(RegistrationRequest request) {
        passwordMatchingChecking(request);
        userUsernameDuplicatingChecking(request);
        userEmailDuplicatingChecking(request);
        User user = getBuildedUser(request);
        userRepository.save(user);
        String jwt = jwtServiceImpl.generateToken(user);
        return getAuthAndRegisterResponse(jwt);
    }


    /**
     * Authenticates a user using the provided authentication request.
     * Performs user authentication and generates a JWT token for the authenticated user.
     *
     * @param request The authentication request containing user credentials.
     * @return An authentication and registration response containing a JWT token upon successful authentication.
     */
    @Override
    public AuthAndRegisterResponse authenticateUser(AuthenticationRequest request) {
        getAuthentication(request);
        User user = findByEmail(request);
        String jwt = jwtServiceImpl.generateToken(user);
        return getAuthAndRegisterResponse(jwt);
    }


    /**
     * Registers a new organization based on the provided organization registration request.
     * Retrieves the admin user by username, checks for organization name duplications,
     * creates and saves the organization, assigns it to the admin user, and updates the user's information.
     *
     * @param request The organization registration request containing organization and admin user information.
     */
    @Override
    public void registerOrganization(OrganizationRegistrationRequest request) {
        User user = getUserByUsername(request.getUsername());
        organizationNameDuplicatingChecking(request);
        Organization organization = modelMapper.map(request, Organization.class);
        organization.setUserId(user);
        organizationService.saveOrganization(organization);
        user.setOrganizationId(organization);
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

    private User getUserByUsername(String username) {
        return userService.findUserByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found with this username")
        );
    }

}
