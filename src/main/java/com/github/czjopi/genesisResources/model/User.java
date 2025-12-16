package com.github.czjopi.genesisResources.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entity representing a user in the system. Stores basic identification data of the user.
 */
@Entity
public class User {
    /**
     * Unique identifier of the user (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * User's first name. Must not be blank.
     */
    @NotBlank(message = "Name must not be blank")
    private String name;

    /**
     * User's surname.
     */
    private String surname;

    /**
     * Personal identifier of the user (exactly 12 characters, unique).
     */
    @NotBlank(message = "Person ID must not be blank")
    @Size(min = 12, max = 12, message = "Person ID must be exactly 12 characters long")
    @Column(unique = true)
    private String personId;

    /**
     * User's UUID (unique).
     */
    @NotBlank(message = "UUID must not be blank")
    @Column(unique = true)
    private String uuid;

    public User() {}

    public User(@NotBlank String name, @NotBlank String surname,
            @NotBlank @Size(min = 12, max = 12) String personId, @NotBlank String uuid) {
        this.name = name;
        this.surname = surname;
        this.personId = personId;
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
