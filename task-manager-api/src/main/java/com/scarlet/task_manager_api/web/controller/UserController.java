package com.scarlet.task_manager_api.web.controller;

import com.scarlet.task_manager_api.domain.User;
import com.scarlet.task_manager_api.service.UserService;
import com.scarlet.task_manager_api.web.dto.UserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User management endpoints")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get all users",
            description = "Returns a list of all registered users"
    )
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Get user by ID",
            description = "Returns a user using its identifier"
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Integer id
    ) {

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @Operation(
            summary = "Create a user",
            description = "Creates a new user and saves it in the database"
    )
    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody UserRequest request
    ) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        User savedUser = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);

    }

    @Operation(
            summary = "Update a user",
            description = "Updates an existing user by its ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(

            @PathVariable Integer id,

            @RequestBody UserRequest request

    ) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        User updatedUser = userService.updateUser(id, user);

        return ResponseEntity.ok(updatedUser);

    }

    @Operation(
            summary = "Delete a user",
            description = "Deletes a user by its ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Integer id
    ) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();

    }

}