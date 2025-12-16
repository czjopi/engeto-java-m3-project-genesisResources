package com.github.czjopi.genesisResources.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.czjopi.genesisResources.model.UserCreateDto;
import com.github.czjopi.genesisResources.model.UserDetailDto;
import com.github.czjopi.genesisResources.model.UserUpdateDto;
import com.github.czjopi.genesisResources.model.UserView;
import com.github.czjopi.genesisResources.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/api")

/**
 * Controller for user-related REST API endpoints. Handles requests for creating, reading, updating,
 * and deleting users.
 */
public class MainController {

    private final UserService userService;

    /**
     * Constructor for MainController.
     * 
     * @param userService the user service to be injected
     */
    public MainController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users, either in detail or short form.
     * 
     * @param detail if true, returns detailed user info; otherwise, returns short info
     * @return list of users in the requested format
     */
    @GetMapping("/users")
    public ResponseEntity<List<? extends UserView>> getUsers(
            @RequestParam(defaultValue = "false") boolean detail) {
        if (detail) {
            return ResponseEntity.ok(userService.getAllUsersDetail());
        } else {
            return ResponseEntity.ok(userService.getAllUsersShort());
        }
    }

    /**
     * Get a user by ID, either in detail or short form.
     * 
     * @param id user ID
     * @param detail if true, returns detailed user info; otherwise, returns short info
     * @return user in the requested format, or 404 if not found
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<? extends UserView> getUserById(@PathVariable @NotNull Integer id,
            @RequestParam(defaultValue = "false") boolean detail) {
        if (detail) {
            return userService.getUserByIdDetail(id).map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return userService.getUserByIdShort(id).map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    /**
     * Create a new user.
     * 
     * @param user user data to create
     * @return created user with details
     */
    @PostMapping("/users")
    public ResponseEntity<UserDetailDto> createUser(@RequestBody @Valid UserCreateDto user) {
        UserDetailDto newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /**
     * Update an existing user.
     * 
     * @param user user data to update
     * @return updated user details, or 404 if not found
     */
    @PutMapping("/users")
    public ResponseEntity<UserDetailDto> updateUser(@RequestBody @Valid UserUpdateDto user) {
        UserDetailDto updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a user by ID.
     * 
     * @param id user ID
     * @return 204 if deleted, 404 if not found
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        boolean userDeleted = userService.deleteUserById(id);
        if (userDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
