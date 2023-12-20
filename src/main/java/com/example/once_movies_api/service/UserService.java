package com.example.once_movies_api.service;

import com.example.once_movies_api.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getUsers();
    UserEntity getUser(Long id);

    UserEntity createUser(UserEntity userEntity);

    UserEntity updateUser(Long id, UserEntity userEntity);

    void deleteUser(Long id);
}
