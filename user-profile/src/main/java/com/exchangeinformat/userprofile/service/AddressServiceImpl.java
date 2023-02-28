package com.exchangeinformat.userprofile.service;

import com.exchangeinformat.userprofile.entityDTO.AddressDTO;
import com.exchangeinformat.userprofile.mappers.AddressMappers;
import com.exchangeinformat.userprofile.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> getAllAddress() {
        return AddressMappers.INSTANCE.addressesToDTOs(addressRepository.findAll());
    }

    @Override
    @Transactional
    public void createAddress(AddressDTO addressDTO) {
        addressRepository.save(AddressMappers.INSTANCE.addressDTOToEntity(addressDTO));
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        return AddressMappers.INSTANCE.addressToDTO(addressRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void updateAddress(AddressDTO addressDTO) {
        addressRepository.save(AddressMappers.INSTANCE.addressDTOToEntity(addressDTO));
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
