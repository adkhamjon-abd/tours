package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final Map<String, Integer> sessions = new HashMap<>();

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String createUser(@RequestBody User user){
        return userRepository.save(user);
    };

    @GetMapping
    public String getAll(){
        return userRepository.findAll();
    }
    // get UserId using token
    public Integer getUserIdFromToken(String token) {
        return sessions.get(token);
    }
}
