package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.model.Roles;
import com.exchangeinformat.userprofile.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RestRoleController {
    private final RoleService roleService;

    @Autowired
    public RestRoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/findOne")
    public ResponseEntity<Roles> getRole(Long id) {
        return ResponseEntity.ok(roleService.getRoles(id));
    }

    @GetMapping
    public ResponseEntity<List<Roles>> getRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createRole(@RequestBody Roles role) {
        roleService.createRole(role);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateRole(@RequestBody Roles role) {
        roleService.updateRoles(role);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteRole(Long id) {
        roleService.deleteRoles(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
