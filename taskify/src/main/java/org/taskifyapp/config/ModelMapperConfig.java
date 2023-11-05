package org.taskifyapp.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.taskifyapp.model.dto.response.TaskResponse;
import org.taskifyapp.model.dto.response.UserResponse;
import org.taskifyapp.model.entity.Task;
import org.taskifyapp.model.entity.User;

@Configuration
public class ModelMapperConfig
{
    @Bean
    public ModelMapper getModelMapper ()
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<User, UserResponse> userToUserResponse = modelMapper.createTypeMap(User.class, UserResponse.class);
        TypeMap<Task, TaskResponse> taskToTaskResponse = modelMapper.createTypeMap(Task.class, TaskResponse.class);

        userToUserResponse.addMapping(src -> src.getOrganization().getId(), UserResponse::setOrganizationId);
        taskToTaskResponse.addMapping(src -> src.getTaskStatus(), TaskResponse::setStatus);

        return modelMapper;
    }
}