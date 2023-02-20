package com.exchangeinformat.userprofile.mappers;

import com.exchangeinformat.userprofile.entity.Address;
import com.exchangeinformat.userprofile.entityDTO.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMappers {

    AddressMappers INSTANCE = Mappers.getMapper(AddressMappers.class);

    AddressDTO addressToDTO(Address user);

    Address addressDTOToEntity(AddressDTO userDTO);

    List<AddressDTO> addressesToDTOs(List<Address> addresses);
}

