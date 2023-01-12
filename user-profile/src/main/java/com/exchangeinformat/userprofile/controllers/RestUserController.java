package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.User;
import com.exchangeinformat.userprofile.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.Timestamp;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/details")
    public String  getUserDetails(Principal principal) {



        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        var token = kp.getToken();
        var cl = token.getClaims();
        var extId = cl.get("sub").toString();
        var username = cl.get("preferred_username").toString();
        var firstName = cl.get("given_name").toString();
        Map<String, List<String>> rolesMap = (Map<String, List<String>>) cl.get("realm_access");
        var roles = rolesMap.get("roles");
        var lastName = cl.get("family_name").toString();
        var email = cl.get("email").toString();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID keycloak равен: ").append(extId).append(System.lineSeparator())
                .append("Ник пользователя: ").append(username).append(System.lineSeparator())
                .append("Имя пользователя: ").append(firstName).append(System.lineSeparator())
                .append("Фамилия пользователя: ").append(lastName).append(System.lineSeparator())
                .append("Емаил пользователя: ").append(email).append(System.lineSeparator())
                .append("Роли пользователя: ");
        for (String role : roles) {
            stringBuilder.append(role).append(System.lineSeparator());
        }



        return stringBuilder.toString();

    }
}
