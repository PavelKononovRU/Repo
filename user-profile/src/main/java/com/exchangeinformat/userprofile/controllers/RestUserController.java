package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.User;
import com.exchangeinformat.userprofile.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class RestUserController {
    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findOne")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<User> getUser(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
