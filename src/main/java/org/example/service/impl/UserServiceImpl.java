package org.example.service.impl;

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

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

         userRepository.findById(user.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("User already exists");
                });

        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        userRepository.deleteById(id);
    }

    public User getById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        return user;
    }

    public User updateUser(int id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userRepository.update(existingUser);
        return existingUser;
    }

    public User patchUser(int id, User updateUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with such id does not exist"));

        if (updateUser.getUsername() != null) {
            existingUser.setUsername(updateUser.getUsername());
        }

        if (updateUser.getPassword() != null) {
            existingUser.setPassword(updateUser.getPassword());
        }

        userRepository.update(existingUser);
        return existingUser;
    }
}
