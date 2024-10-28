package com.example.demo.service.user;

import com.example.demo.model.user.User;

import java.util.List;

public interface UsersService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String id);
    User updateUser(String id, User user);
    void deleteUser(String id);
}
