package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<Integer, User> users = new HashMap<>();
    private int nextId = 2;

    public UserRepository(){
        users.put(1, new User(1, "admin", "admin"));
    }

    public String save(User user){
        for (User existing : users.values()) {
            if (existing.getUsername().equals(user.getUsername())) {
                return "Username already exists. Please choose another.";
            }
        }
        user.setId(nextId++);
        users.put(user.getId(), user);
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
        User user = users.get(id);
        if (user == null) {
            return null;
        }
        return user;
    }


    public String deleteById(int id) {
        users.entrySet().removeIf(entry -> entry.getValue().getId() == id);
        return "User Deleted";
    }
}
