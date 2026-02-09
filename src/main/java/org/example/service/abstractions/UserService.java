package org.example.service.abstractions;

import org.example.dto.UserDTO;
import org.example.dto.request.CreateUserRequest;
import org.example.dto.request.UpdateUserRequest;
import org.example.dto.response.*;
import org.example.model.Tour;
import org.example.model.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest userRequest);
    List<UserResponse> getAll();
    void deleteUser(int id);
    UserResponse getById(int id);
    UserResponse updateUser(int id, UpdateUserRequest user);
    UserResponse patchUser(int id, UpdateUserRequest updateUser);
}
