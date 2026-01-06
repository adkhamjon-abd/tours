package org.example.controller;

import org.example.dto.UserDTO;
import org.example.model.User;
import org.example.response.ApiResponse;
import org.example.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody User user) {
        UserDTO createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdUser));
    }

    ;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAll() {
        List<UserDTO> users = userService.getAll();
        return ResponseEntity.ok().body(new ApiResponse<>(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getById(@PathVariable("id") int id) {
        UserDTO user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(
            @PathVariable("id") int id,
            @RequestBody User user
    ) {
        UserDTO updated = userService.updateUser(id, user);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> patchUser(
            @PathVariable("id") int id,
            @RequestBody User user
    ) {
        UserDTO updatedUser = userService.patchUser(id, user);
        if (updatedUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(updatedUser));
    }
}
