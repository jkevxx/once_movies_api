package com.example.once_movies_api.controller;

import com.example.once_movies_api.dto.UserDTO;
import com.example.once_movies_api.entity.UserEntity;
import com.example.once_movies_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<UserDTO> users = userService.getUsers().stream().map(this::userToUserDTO).collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {

        UserEntity user = userService.getUser(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return ResponseEntity.ok(userToUserDTO(user));
    }

    @GetMapping("/allUsers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User created successfully")
    public UserEntity createUser( @Valid @RequestBody UserEntity user) {

        try {
            return userService.createUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "User updated successfully")
    public UserEntity updateUser(@PathVariable Long id, @Valid @RequestBody UserEntity user) {

        try {
            return userService.updateUser(id, user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "User deleted successfully")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
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
