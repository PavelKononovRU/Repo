package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();

    void createAddress(Address address);

    Address getAddressById(Long id);

    void updateAddress(Address address);

    void deleteAddress(Long id);
}
