package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entity.Roles;
import com.exchangeinformat.userprofile.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void createRole(Roles roles) {
        roleRepository.save(roles);
    }

    @Override
    public Roles getRoles(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void updateRoles(Roles roles) {
        roleRepository.save(roles);
    }

    @Override
    @Transactional
    public void deleteRoles(Long id) {
        roleRepository.deleteById(id);
    }
}
