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
}
