package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAllAddress();

    void createAddress(AddressDTO addressDTO);

    AddressDTO getAddressById(Long id);

    void updateAddress(AddressDTO addressDTO);

    void deleteAddress(Long id);
}
