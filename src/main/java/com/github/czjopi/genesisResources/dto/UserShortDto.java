package com.github.czjopi.genesisResources.dto;

/**
 * Data Transfer Object (DTO) representing a short version of a user. Contains only basic
 * identification data.
 */
public class UserShortDto {
    private Integer id;
    private String name;
    private String surname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
