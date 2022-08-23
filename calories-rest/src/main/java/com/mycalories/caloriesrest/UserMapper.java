package com.mycalories.caloriesrest;

import com.mycalories.caloriesrest.dto.UserDTO;
import com.mycalories.model2.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO entityToDto(User entity);

    User dtoToEntity(UserDTO dto);

    void dtoToEntity(UserDTO dto, @MappingTarget User entity);

    List<UserDTO> entityListToDTOList(List<User> entitylist);
}
