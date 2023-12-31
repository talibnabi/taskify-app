package org.taskifyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.taskifyapp.exception.custom.UserNotFoundException;
import org.taskifyapp.model.dto.response.UserResponse;
import org.taskifyapp.model.entity.User;
import org.taskifyapp.repository.UserRepository;
import org.taskifyapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse getUserResponseById(Long id) {
        User user = getUser(id);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public UserResponse getUserResponseByUsername(String username) {
        User user = getUserByUsername(username);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public UserResponse getUserResponseByEmail(String email) {
        User user = getUserByEmail(email);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public List<UserResponse> getUserResponseByOrganizationId(Long organizationId) {
        List<User> userList = userList(organizationId);
        List<UserResponse> userResponses = userResponseList(userList);
        return userResponses;
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
    }

    private User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );
    }

    private User getUser(Long id) {
        return userRepository.findUserById(id).orElseThrow(
                () -> new UserNotFoundException("User not found."));
    }

    private List<User> userList(Long organizationId) {
        return userRepository.findUserByOrganizationId(organizationId).orElseThrow(
                () -> new UserNotFoundException("User not found"));
    }

    private List<UserResponse> userResponseList(List<User> userList) {
        return userList
                .stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

}
