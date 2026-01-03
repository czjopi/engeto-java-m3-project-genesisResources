package com.github.czjopi.genesisResources.mapper;

import com.github.czjopi.genesisResources.dto.UserDetailDto;
import com.github.czjopi.genesisResources.dto.UserShortDto;
import com.github.czjopi.genesisResources.model.User;

/**
 * Mapper class for converting User entities to DTOs.
 */
public class UserMapper {

    public static UserShortDto toUserShortDto(User user) {
        UserShortDto dto = new UserShortDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }

    public static UserDetailDto toUserDetailDto(User user) {
        UserDetailDto dto = new UserDetailDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPersonId(user.getPersonId());
        dto.setUuid(user.getUuid());
        return dto;
    }

}
