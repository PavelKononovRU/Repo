package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.Address;
import com.exchangeinformat.userprofile.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class RestAddressController {
    private final AddressService addressService;

    public RestAddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/findOne")
    public ResponseEntity<Address> getAddress(Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses() {
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createAddress(@RequestBody Address address) {
        addressService.createAddress(address);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateAddress(@RequestBody Address address) {
        addressService.updateAddress(address);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAddress(Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
