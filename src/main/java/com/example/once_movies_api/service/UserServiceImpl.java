package com.example.once_movies_api.service;

import com.example.once_movies_api.dto.UserDTO;
import com.example.once_movies_api.entity.UserEntity;
import com.example.once_movies_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.findAll().stream().map(this::userToUserDTO).toList();

        if (users.isEmpty()) {
            throw new RuntimeException("Users not found");
        }

        return users;
    }

    @Override
    public UserDTO getUser(Long id) {
//        UserDTO user = userRepository.findById(id).map(this::userToUserDTO).orElse(null);
        UserEntity user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return userToUserDTO(user);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {

        UserEntity userFound = userRepository.findById(id).orElse(null);

        if (userFound == null) {
            throw new RuntimeException("User not found");
        }

        userFound.setName(updatedUserDTO.getName());
        userFound.setLastName(updatedUserDTO.getLastName());
        userFound.setEmail(updatedUserDTO.getEmail());
        userFound.setPassword(userFound.getPassword());

        userRepository.save(userFound);

        return userToUserDTO(userFound);
    }

    @Override
    public void deleteUser(Long id) {

        UserEntity user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    private UserDTO userToUserDTO(UserEntity userEntity) {

        UserDTO userDTO = new UserDTO();
//        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        return userDTO;
    }
}
