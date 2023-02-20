package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    void createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    void updateUser(UserDTO userDTO);

    void deleteUser(Long id);

    UserDTO getUserByExtId(String extId);

    boolean isUserPresent(String extId);
}
