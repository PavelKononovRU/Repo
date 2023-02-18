package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.User;
import com.exchangeinformat.userprofile.entityDTO.UserDTO;
import com.exchangeinformat.userprofile.mappers.UserMappers;
import com.exchangeinformat.userprofile.service.UserService;
import com.exchangeinformat.userprofile.util.Data;
import com.exchangeinformat.userprofile.util.ValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.Map;

@Tag(name = "Контроллер пользователя", description = "CRUD операции с пользователями")
@RestController
@RequestMapping("api/user")
public class RestUserController {
    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получение пользователя", description = "Метод принимает в параметры long ID пользователя, доступ только у админа")
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Получение всех пользователей", description = "Доступ только у админа")
    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Создание пользователя", description = "Метод принимает в параметры UserDTO, доступ только у админа")
    @PostMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Обновление пользователя", description = "Метод принимает в параметры UserDTO (нужно передать в теле запроса), доступ только у админа")
    @PutMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление пользователя", description = "Метод принимает в параметры long ID, доступ только у админа")
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable  Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Домашняя страница пользователя", description = "В параметры автоматически передается Principal (самому ничего передавать не нужно), доступ у пользователя и админа")
    @GetMapping("/home")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<User>  getUserDetails(Principal principal) {
        Map<String, Object> cl = getExtID(principal);
        User user;
        String extId = cl.get("sub").toString();
        if (!userService.isUserPresent(extId)) {
            user = User.builder()
                .extId(extId)
                .username(cl.get("preferred_username").toString())
                .firstName(cl.get("given_name").toString())
                .lastName(cl.get("family_name").toString())
                .email(cl.get("email").toString())
                .build();
            userService.createUser(user);
        } else {
            user = userService.getUserByExtId(extId);
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Обновление пользователя после регистрации", description = "В параметры передается Principal(автоматически) и UserDTO (нужно передать в теле запроса), доступ у админа и пользователя")
    @PostMapping(value = "/update")
    @RolesAllowed({"USER"})
    public ResponseEntity<ValidationResponse> updatingUserFields(Principal principal, @RequestBody @Valid UserDTO userDTO) {
        Map<String, Object> cl = getExtID(principal);
        String extId = cl.get("sub").toString();
        if (!userService.isUserPresent(extId) || !extId.equals(userDTO.getExtId())) { //Проверка, что пользователь существует с таким extID и проверка равенства extID principal и переданного юзера
            return ResponseEntity.status(500).body(new ValidationResponse(new Data("Не удалось обновить пользователя")));
        } else {
            userService.updateUser(UserMappers.INSTANCE.userDTOToEntity(userDTO));
            return ResponseEntity.status(200).body(new ValidationResponse(new Data("Данные успешно сохранены")));
        }
    }

    private Map<String, Object> getExtID(Principal principal) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        return token.getClaims();
    }
}
