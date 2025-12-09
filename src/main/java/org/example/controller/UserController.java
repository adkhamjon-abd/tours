package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") int id) { return userService.getById(id);}

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") int id,
            @RequestBody User user
    ) {
        User updated = userService.updateUser(id, user);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(
            @PathVariable("id") int id,
            @RequestBody User user
    ) {
        User updatedUser = userService.patchUser(id, user);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedUser);
    }
}
