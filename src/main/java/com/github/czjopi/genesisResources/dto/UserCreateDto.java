package com.github.czjopi.genesisResources.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) used for creating a new user.
 */
public class UserCreateDto {
    @NotBlank(message = "Name must not be blank")
    private String name;

    private String surname;

    @NotBlank(message = "Person ID must not be blank")
    @Size(min = 12, max = 12, message = "Person ID must be exactly 12 characters long")
    private String personId;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonId() {
        return personId;
    }
}
