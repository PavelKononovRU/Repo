package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.UserDTO;
import com.exchangeinformat.userprofile.mappers.UserMappers;
import com.exchangeinformat.userprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMappers.INSTANCE.usersTODTO(userRepository.findAll());
    }

    @Override
    @Transactional
    public void createUser(UserDTO userDTO) {
        userRepository.save(UserMappers.INSTANCE.userDTOToEntity(userDTO));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return UserMappers.INSTANCE.userToDTO(userRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        userRepository.save(UserMappers.INSTANCE.userDTOToEntity(userDTO));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserByExtId(String extId) {
        return UserMappers.INSTANCE.userToDTO(userRepository.findByExtId(extId).orElse(null));
    }

    @Override
    public boolean isUserPresent(String extId) {
        return userRepository.existsByExtId(extId);
    }
}
