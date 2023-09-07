package com.example.practicheskaya2.dao;

import com.example.practicheskaya2.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDao {
    private static int USER_COUNT;
    private List<User> user;

    {
        user = new ArrayList<>();

        user.add(new User(++USER_COUNT, "Пользователь 1", "user1@example.com", "пароль1"));
        user.add(new User(++USER_COUNT, "Пользователь 2", "user2@example.com", "пароль2"));
        user.add(new User(++USER_COUNT, "Пользователь 3", "user3@example.com", "пароль3"));
        user.add(new User(++USER_COUNT, "Пользователь 4", "user4@example.com", "пароль4"));
        user.add(new User(++USER_COUNT, "Пользователь 5", "user5@example.com", "пароль5"));
        user.add(new User(++USER_COUNT, "Пользователь 6", "user6@example.com", "пароль6"));
        user.add(new User(++USER_COUNT, "Пользователь 7", "user7@example.com", "пароль7"));
        user.add(new User(++USER_COUNT, "Пользователь 8", "user8@example.com", "пароль8"));
        user.add(new User(++USER_COUNT, "Пользователь 9", "user9@example.com", "пароль9"));
        user.add(new User(++USER_COUNT, "Пользователь 10", "user10@example.com", "пароль10"));
    }

    public List<User> index() {
        return user;
    }

    public User show(int id) {
        return user.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void save(User newUser) {
        newUser.setId(++USER_COUNT);
        user.add(newUser);
    }

    public void update(int id, User updatedUser) {
        User user = show(id);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
        }
    }

    public void delete(int id) {
        user = user.stream()
                .filter(u -> u.getId() != id)
                .collect(Collectors.toList());
    }






}
