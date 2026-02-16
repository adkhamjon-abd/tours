package org.example.service.impl;

import jakarta.validation.Valid;
import org.example.dto.mapper.UserMapper;
import org.example.dto.request.CreateUserRequest;
import org.example.dto.request.UpdateUserRequest;
import org.example.dto.response.UserResponse;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;

import org.example.service.abstractions.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(@Valid CreateUserRequest userRequest) {
        // Convert the request to entity
        User user = userMapper.toEntity(userRequest);

        userRepository.findById(user.getId()).ifPresent(u -> {
            throw new UserAlreadyExistsException("User already exists");
        });

        return userMapper.toResponse(userRepository.save(user));
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    public void deleteUser(int id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        userRepository.deleteById(id);
    }

    public UserResponse getById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toResponse(user);
    }

    public UserResponse updateUser(int id, UpdateUserRequest updateUserRequest) {
        // Convert request to entity
        User user = userMapper.toEntity(updateUserRequest);
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));


        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userRepository.update(existingUser);
        return userMapper.toResponse(existingUser);
    }

    public UserResponse patchUser(int id, UpdateUserRequest updateUserRequest) {
        // Convert request to entity
        User user = userMapper.toEntity(updateUserRequest);
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (user.getUsername() != null) {
            existingUser.setUsername(updateUserRequest.getUsername());
        }

        if (user.getPassword() != null) {
            existingUser.setPassword(updateUserRequest.getPassword());
        }

        userRepository.update(existingUser);
        return userMapper.toResponse(existingUser);
    }
}
