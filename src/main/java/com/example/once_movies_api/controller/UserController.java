package com.example.once_movies_api.controller;

import com.example.once_movies_api.dto.UserDTO;
import com.example.once_movies_api.entity.UserEntity;
import com.example.once_movies_api.exception.ResourceNotFoundException;
import com.example.once_movies_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
            throw new ResourceNotFoundException(e.getMessage());
        }
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
    public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO user) {
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

}
