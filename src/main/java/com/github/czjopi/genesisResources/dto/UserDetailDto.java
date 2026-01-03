package com.github.czjopi.genesisResources.dto;

/**
 * Data Transfer Object (DTO) representing a detailed version of a user. Contains all identification
 * data.
 */
public class UserDetailDto extends UserShortDto {
    private String personId;
    private String uuid;

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
