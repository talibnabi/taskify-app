package org.taskifyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.taskifyapp.exception.custom.OrganizationIsNotSamenessException;
import org.taskifyapp.exception.custom.TaskNotFoundException;
import org.taskifyapp.exception.custom.UserNotFoundException;
import org.taskifyapp.model.dto.request.TaskCreationRequest;
import org.taskifyapp.model.dto.request.TaskUpdatingRequest;
import org.taskifyapp.model.dto.response.TaskResponse;
import org.taskifyapp.model.dto.response.UserResponse;
import org.taskifyapp.model.entity.Task;
import org.taskifyapp.model.entity.User;
import org.taskifyapp.repository.TaskRepository;
import org.taskifyapp.repository.UserRepository;
import org.taskifyapp.service.TaskService;
import org.taskifyapp.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = getTask(id);
        TaskResponse taskResponse = modelMapper.map(task, TaskResponse.class);
        return taskResponse;
    }

    @Override
    public List<TaskResponse> getAllTaskList(Long userId) {
        List<Task> tasks = getAllTask(userId);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(task -> modelMapper.map(task, TaskResponse.class))
                .collect(Collectors.toList());
        return taskResponses;
    }

    @Override
    public void update(TaskUpdatingRequest request) {
        String adminUserName = getAdminUsernameFromSecurityContextHolder();
        User admin = getUser(adminUserName);
        getTask(request.getTaskId());
        if (organizationSamenessChecker(admin, getUser(request.getReceiverId()))) {
            Task task = modelMapper.map(request, Task.class);
            taskRepository.save(task);
        }
    }

    @Override
    public void create(TaskCreationRequest request) {
        String adminUsername = getAdminUsernameFromSecurityContextHolder();
        User sender = getUser(adminUsername);
        System.out.println(sender.getOrganization().getId());
        UserResponse receiver = getUserResponse(request.getReceiverId());
        if (organizationSamenessChecker(sender, receiver)) {
            Task task = modelMapper.map(request, Task.class);
            Task buildedTask = buildTask(task, sender, receiver, request);
            taskRepository.save(buildedTask);
        }
    }


    @Override
    public void delete(Long id) {
        String adminUsername = getAdminUsernameFromSecurityContextHolder();
        User sender = getUser(adminUsername);
        if (taskDeletionChecker(getTaskById(id), sender)) {
            taskRepository.deleteById(id);
        }
    }

    private UserResponse getUserResponse(Long id) {
        UserResponse receiver = userService.getUserResponseById(id);
        return receiver;
    }

    private boolean taskDeletionChecker(TaskResponse taskResponse, User receiver) {
        if (Objects.isNull(taskResponse.getSenderId()) || !taskResponse.getSenderId().equals(receiver.getId())) {
            throw new TaskNotFoundException("Task not found.");
        }
        return true;
    }

    private Task buildTask(Task task, User sender, UserResponse receiver, TaskCreationRequest request) {
        return task.builder()
                .senderId(sender.getId())
                .title(request.getTaskTitle())
                .description(request.getTaskDescription())
                .taskStatus(request.getTaskStatus())
                .receiverId(receiver.getId())
                .deadline(request.getTaskDeadline())
                .build();
    }

    private User getUser(String adminUsername) {
        return userRepository.findByEmail(adminUsername).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private boolean organizationSamenessChecker(User sender, UserResponse receiver) {
        if (Objects.isNull(sender.getOrganization().getId()) ||
                !sender.getOrganization().getId().equals(receiver.getOrganizationId())) {
            throw new OrganizationIsNotSamenessException("Organizations is not sameness");
        }
        return true;
    }


    private List<Task> getAllTask(Long userId) {
        User user = userRepository.findUserById(userId).orElseThrow();
        List<Task> taskList = user.getTasks();
        return taskList;
    }

    private Task getTask(Long id) {
        return taskRepository.findTaskById(id).orElseThrow(
                () -> new TaskNotFoundException("Task not found exception. ")
        );
    }

    private UserResponse getUser(Long id) {
        return userService.getUserResponseById(id);
    }


    private String getAdminUsernameFromSecurityContextHolder() {
        return ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
