package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entityDTO.UserDTO;
import com.exchangeinformat.userprofile.mappers.UserMappers;
import com.exchangeinformat.userprofile.service.UserInfoService;
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
import org.springframework.cloud.stream.function.StreamBridge;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Tag(name = "Контроллер пользователя", description = "CRUD операции с пользователями")
@RestController
@RequestMapping("api/user")
public class RestUserController {
    private final UserService userService;
    private final StreamBridge streamBridge;
    private final UserInfoService userInfoService;

    @Autowired
    public RestUserController(UserService userService, StreamBridge streamBridge, UserInfoService userInfoService) {
        this.userService = userService;
        this.streamBridge = streamBridge;
        this.userInfoService = userInfoService;
    }

    @Operation(summary = "Получение пользователя", description = "Метод принимает в параметры long ID пользователя, доступ только у админа")
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Получение всех пользователей", description = "Доступ только у админа")
    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Создание пользователя", description = "Метод принимает в параметры UserDTO, доступ только у админа")
    @PostMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Обновление пользователя", description = "Метод принимает в параметры UserDTO, доступ только у админа")
    @PutMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление пользователя", description = "Метод принимает в параметры long ID, доступ только у админа")
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Домашняя страница пользователя", description = "В параметры автоматически передается Principal (самому ничего передавать не нужно), доступ у пользователя и админа")
    @GetMapping("/home")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<UserDTO> getUserDetails(Principal principal) {
        Map<String, Object> cl = getClaims(principal);
        UserDTO userDTO;
        String extId = cl.get("sub").toString();
        if (!userService.isUserPresent(extId)) {
            userDTO = UserDTO.builder()
                    .extId(extId)
                    .username(cl.get("preferred_username").toString())
                    .firstName(cl.get("given_name").toString())
                    .lastName(cl.get("family_name").toString())
                    .email(cl.get("email").toString())
                    .build();
            userService.createUser(userDTO);
        } else {
            userDTO = userService.getUserByExtId(extId);
        }
        return ResponseEntity.ok(userDTO);
    }

    @Operation(summary = "Обновление пользователя после регистрации", description = "В параметры передается Principal(автоматически) и UserDTO (нужно передать в теле запроса), доступ у админа и пользователя")
    @PostMapping(value = "/update")
    @RolesAllowed({"USER"})
    public ResponseEntity<ValidationResponse> updatingUserFields(Principal principal, @RequestBody @Valid UserDTO userDTO) {
        Map<String, Object> cl = getClaims(principal);
        String extId = cl.get("sub").toString();
        if (!userService.isUserPresent(extId) || !extId.equals(userDTO.getExtId())) { //Проверка, что пользователь существует с таким extID и проверка равенства extID principal и переданного юзера
            return ResponseEntity.status(500).body(new ValidationResponse(new Data("Не удалось обновить пользователя")));
        } else {
            userService.updateUser(userDTO);
            return ResponseEntity.status(200).body(new ValidationResponse(new Data("Данные успешно сохранены")));
        }
    }

    @GetMapping(value = "/getInfo")
    @RolesAllowed({"USER"})
    public String getInfo(Principal principal) throws InterruptedException {
        Map<String, Object> cl = getClaims(principal);
        String extId = cl.get("sub").toString();
        if (userInfoService.getById(extId) != null && ChronoUnit.HOURS.between(userInfoService.getById(extId).getLastRequest(), LocalDateTime.now()) < 1) {
            return "ИНФО ИЗ БАЗЫ USER: " + userInfoService.getById(extId);
        }
        streamBridge.send("producer-out-0", extId);
        Thread.sleep(1000);
        return "ИНФО ИЗ quotes: " + userInfoService.getById(extId);
    }

    private Map<String, Object> getClaims(Principal principal) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        return token.getClaims();
    }
}