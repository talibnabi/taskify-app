package org.taskifyapp.mapper;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.taskifyapp.model.dto.request.RegistrationRequest;
import org.taskifyapp.model.entity.User;


@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User registrationRequestToUserMapper(RegistrationRequest request) {
        return modelMapper.map(request, User.class);
    }
}
