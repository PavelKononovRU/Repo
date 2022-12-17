package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.model.Address;
import com.exchangeinformat.userprofile.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
