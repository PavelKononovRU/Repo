package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(User user);

    User getUserById(Long id);

    void updateUser( User user);

    void deleteUser(Long id);

    User getUserByExtId(String extId);
}
