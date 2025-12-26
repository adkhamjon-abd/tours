package org.example.service.abstractions;

import org.example.model.Tour;
import org.example.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAll();
    void deleteUser(int id);
    User getById(int id);
    User updateUser(int id, User user);
    User patchUser(int id, User updateUser);
}
