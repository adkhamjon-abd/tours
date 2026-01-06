package org.example.service.impl;

import org.example.dto.UserDTO;
import org.example.dto.mapper.UserMapper;
import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;

import org.example.service.abstractions.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(User user) {

        userRepository.findById(user.getId()).ifPresent(u -> {
            throw new UserAlreadyExistsException("User already exists");
        });

        return userMapper.userToUserDTO(userRepository.save(user));
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
    }

    public void deleteUser(int id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        userRepository.deleteById(id);
    }

    public UserDTO getById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        return userMapper.userToUserDTO(user);
    }

    public UserDTO updateUser(int id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userRepository.update(existingUser);
        return userMapper.userToUserDTO(existingUser);
    }

    public UserDTO patchUser(int id, User updateUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        if (updateUser.getUsername() != null) {
            existingUser.setUsername(updateUser.getUsername());
        }

        if (updateUser.getPassword() != null) {
            existingUser.setPassword(updateUser.getPassword());
        }

        userRepository.update(existingUser);
        return userMapper.userToUserDTO(existingUser);
    }
}
