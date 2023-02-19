package com.exchangeinformat.userprofile.mappers;

import com.exchangeinformat.userprofile.entity.User;
import com.exchangeinformat.userprofile.entityDTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMappers {

    UserMappers INSTANCE = Mappers.getMapper(UserMappers.class);

    UserDTO userToDTO(User user);

    User userDTOToEntity(UserDTO userDTO);

    List<UserDTO> usersTODTO(List<User> users);
}
