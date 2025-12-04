package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService =  userService;
    }

    @PostMapping("/register")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    };

    @GetMapping
    public String getAll(){
        return userService.getAll();
    }

}
