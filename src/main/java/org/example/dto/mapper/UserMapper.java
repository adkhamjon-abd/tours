package org.example.dto.mapper;

import org.example.dto.UserDTO;
import org.example.dto.request.CreateUserRequest;
import org.example.dto.request.UpdateUserRequest;
import org.example.dto.response.UserResponse;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring") // componentModel = "spring" allows Spring to manage the mapper as a Spring bean
public interface UserMapper {
    // Request → Entity
    User toEntity(CreateUserRequest createUserRequest);

    User toEntity(UpdateUserRequest updateUserRequest);

    void updateEntity(UpdateUserRequest request, @MappingTarget User user);

    // Entity → Response
    UserResponse toResponse(User user);

}

