package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.Address;
import com.exchangeinformat.userprofile.entityDTO.AddressDTO;
import com.exchangeinformat.userprofile.mappers.AddressMappers;
import com.exchangeinformat.userprofile.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер адреса проживания пользователя", description = "CRUD операции с адресом пользователя")
@RestController
@RequestMapping("/api/address")
public class RestAddressController {
    private final AddressService addressService;

    public RestAddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Получение адреса", description = "Метод принимает в параметры long ID адреса, доступ только у админа")
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @Operation(summary = "Получение всех адресов", description = "Доступ только у админа")
    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAddresses() {
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @Operation(summary = "Создание адреса", description = "Метод в параметры принимает AddressDTO, доступ только у админа, для создания используйте id == 0")
    @RolesAllowed({"ADMIN"})
    @PostMapping
    public ResponseEntity<HttpStatus> createAddress(@RequestBody AddressDTO addressDTO) {
        addressService.createAddress(addressDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Обновление адреса", description = "Метод в параметры принимает AddressDTO, доступ только у админа, для обновления используйте id, обновляемого адреса")
    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<HttpStatus> updateAddress(@RequestBody AddressDTO addressDTO) {
        addressService.updateAddress(addressDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление адреса", description = "Метод в параметры принимает AddressDTO, доступ только у админа, для обновления используйте id, обновляемого пользователя")
    @RolesAllowed({"ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
