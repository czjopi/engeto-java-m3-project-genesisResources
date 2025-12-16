package com.github.czjopi.genesisResources.model;

/**
 * Data Transfer Object (DTO) representing a short version of a user. Contains only basic
 * identification data.
 */
public class UserShortDto implements UserView {
    private int id;
    private String name;
    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
