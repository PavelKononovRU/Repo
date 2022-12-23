package com.exchangeinformat.userprofile.service;
import com.exchangeinformat.userprofile.model.Roles;
import java.util.List;

public interface RoleService {

    void createRole(Roles roles);

    Roles getRoles(Long id);

    List<Roles> getAllRoles();

    void updateRoles(Roles roles);

    void deleteRoles(Long id);

}
