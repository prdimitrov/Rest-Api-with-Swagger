package com.digitalrazgrad.RestApiWithSwagger.service;

import com.digitalrazgrad.RestApiWithSwagger.model.entity.User;

import java.util.Set;

public interface UserService {
    Set<User> getAllUsers();

    User addUser(User user);

    User updateUser(long userId, User user);

    void deleteUser(long userId);
}
