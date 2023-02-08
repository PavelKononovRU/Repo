package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.User;
import com.exchangeinformat.userprofile.entityDTO.UserDTO;
import com.exchangeinformat.userprofile.mappers.UserMappers;
import com.exchangeinformat.userprofile.service.UserService;
import com.exchangeinformat.userprofile.util.Data;
import com.exchangeinformat.userprofile.util.ValidationResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @RolesAllowed({"ADMIN"})
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

    @GetMapping("/home")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<User>  getUserDetails(Principal principal) {

        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        var cl = token.getClaims();

        User user = new User();
        String extId = cl.get("sub").toString();
        if (!userService.isUserPresent(extId)) {
            user.setExtId(extId);
            user.setUsername(cl.get("preferred_username").toString());
            user.setFirstName(cl.get("given_name").toString());
            user.setLastName(cl.get("family_name").toString());
            user.setEmail(cl.get("email").toString());
            userService.createUser(user);
        } else {
            user = userService.getUserByExtId(extId);
        }

        return ResponseEntity.ok(user);
    }


    @PostMapping(value = "/update")
    @RolesAllowed({"USER"})
    public ResponseEntity<ValidationResponse> getWord(Principal principal, @RequestBody @Valid UserDTO userDTO) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        System.out.println(userDTO);
        var cl = token.getClaims();

        String extId = cl.get("sub").toString();
        if (!userService.isUserPresent(extId)) {
            return ResponseEntity.status(500).body(new ValidationResponse(new Data("нет такого юзера 500")));
        } else {
            userService.updateUser(UserMappers.INSTANCE.userDTOToEntity(userDTO));
            return ResponseEntity.status(200).body(new ValidationResponse(new Data("Данные успешно сохранены")));
        }
    }
}
