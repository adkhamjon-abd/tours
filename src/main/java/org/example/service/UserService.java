package org.example.service;

import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        User existing = userRepository.findById(user.getId());
        if (existing != null){
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id);
        if (user == null){
            throw new UserNotFoundException("User with such id does not exist");
        }
        userRepository.deleteById(id);
    }

    public User getById(int id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new UserNotFoundException("User with such id does not exist");
        }

        return userRepository.findById(id);
    }

    public User updateUser(int id, User user) {
        User existingUser = userRepository.findById(id);

        if (existingUser == null) {
            return null;
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userRepository.update(existingUser);
        return existingUser;
    }

    public User patchUser(int id, User updateUser) {
        User existingUser = userRepository.findById(id);

        if (existingUser == null) {return null;}

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
