package com.github.czjopi.genesisResources.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) used for updating an existing user.
 */
public class UserUpdateDto {
    @NotNull(message = "ID must not be blank")
    @Positive(message = "ID must be a positive integer")
    private int id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    private String surname;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
