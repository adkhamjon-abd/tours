package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.request.CreateUserRequest;
import org.example.dto.request.UpdateUserRequest;
import org.example.dto.response.UserResponse;
import org.example.response.ApiResponse;
import org.example.service.abstractions.UserService;
import org.example.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid CreateUserRequest userRequest) {
        UserResponse createdUser = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdUser));
    }

    ;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
        List<UserResponse> users = userService.getAll();
        return ResponseEntity.ok().body(new ApiResponse<>(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable("id") int id) {
        UserResponse user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable("id") int id,
            @RequestBody UpdateUserRequest updateUserRequest
    ) {
        UserResponse updated = userService.updateUser(id, updateUserRequest);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new ApiResponse<>(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> patchUser(
            @PathVariable("id") int id,
            @RequestBody UpdateUserRequest updateUserRequest
            ) {
        UserResponse updatedUser = userService.patchUser(id, updateUserRequest);
        if (updatedUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(updatedUser));
    }
}
