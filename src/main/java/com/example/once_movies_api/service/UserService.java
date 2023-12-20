package com.example.once_movies_api.service;

import com.example.once_movies_api.dto.UserDTO;
import com.example.once_movies_api.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUser(Long id);

    UserEntity createUser(UserEntity userEntity);

    UserDTO updateUser(Long id, UserDTO updatedUserDTO);

    void deleteUser(Long id);
}
