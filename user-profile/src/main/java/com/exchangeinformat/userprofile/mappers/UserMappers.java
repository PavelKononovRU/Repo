package com.exchangeinformat.userprofile.mappers;

import com.exchangeinformat.userprofile.entity.User;
import com.exchangeinformat.userprofile.entityDTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMappers {

    UserMappers INSTANCE = Mappers.getMapper(UserMappers.class);

    UserDTO userToDTO (User user);

    User userDTOToEntity(UserDTO userDTO);
}
