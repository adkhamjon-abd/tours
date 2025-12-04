package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;
    public UserRepository(){
        save(new User(0, "admin", "admin"));
    }

    public String save(User user){
        for (User existing : users.values()) {
            if (existing.getUsername().equals(user.getUsername())) {
                return "Username already exists. Please choose another.";
            }
        }
        user.setId(nextId);
        users.put(nextId, user);
        nextId++;
        return "User: " + user.getUsername() + " created with id: " + user.getId();
    }

    public User findByUsername(String userName){
        User user = users.values().stream()
                .filter(user1 -> user1.getUsername().equals(userName))
                .toList().get(0);

        return user;
    }

    public String findAll() {
        String result = "";
        for (User user : users.values()) {
            String username = user.getUsername();
            String password = user.getPassword();

            // Corrected concatenation and newline handling
            result += " UserName: " + username + " Password:" + password + "\n";
        }
        return result;
    }

    public User findById(int id){
        return users.get(id);
    }


    public String deleteById(int id) {
        users.entrySet().removeIf(entry -> entry.getValue().getId() == id);
        return "User Deleted";
    }
}
