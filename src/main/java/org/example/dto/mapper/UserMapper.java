package org.example.dto.mapper;

import org.example.dto.UserDTO;
import org.example.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // componentModel = "spring" allows Spring to manage the mapper as a Spring bean
public interface UserMapper {
    UserDTO userToUserDTO(User user);
}

