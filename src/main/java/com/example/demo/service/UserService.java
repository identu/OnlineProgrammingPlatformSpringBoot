package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User createUser(User user);
    void deleteUser(Long userId);

    User getUserByUsernameAndPassword(String username, String password);

    User updateUser(User user);
}