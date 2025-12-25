package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private Map<Integer, User> users = new HashMap<>();
    private int nextId = 2;

    public UserRepository(){
        users.put(1, new User(1, "admin", "admin"));
    }

    public User save(User user){

        user.setId(nextId++);
        users.put(user.getId(), user);
        return user;
    }

//    public Optional<User> findByUsername(String userName){
//        User user = users.values().stream()
//                .filter(user1 -> user1.getUsername().equals(userName))
//                .toList().get(0);
//
//        return Optional.ofNullable(user);
//    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> findById(int id){
        User user = users.values().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(user);
    }


    public void deleteById(int id) {
        users.entrySet().removeIf(entry -> entry.getValue().getId() == id);
    }

    public User update(User existingUser) {
        users.replace(existingUser.getId(), existingUser);
        return existingUser;
    }
}
