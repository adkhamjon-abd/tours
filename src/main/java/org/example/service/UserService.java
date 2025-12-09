package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public String getAll() {
        return userRepository.findAll();
    }

    public String createUser(User user) {
        return userRepository.save(user);
    }

    public String deleteUser(int id) {
        return userRepository.deleteById(id);
    }

    public User getById(int id) {
        return userRepository.findById(id);
    }

    public User updateUser(int id, User user) {
        User existingUser = userRepository.findById(id);

        if (existingUser == null) {
            return null;
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
        return existingUser;
    }
}
