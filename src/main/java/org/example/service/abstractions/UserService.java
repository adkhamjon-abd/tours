package org.example.service.abstractions;

import org.example.dto.UserDTO;
import org.example.model.Tour;
import org.example.model.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(User user);
    List<UserDTO> getAll();
    void deleteUser(int id);
    UserDTO getById(int id);
    UserDTO updateUser(int id, User user);
    UserDTO patchUser(int id, User updateUser);
}
