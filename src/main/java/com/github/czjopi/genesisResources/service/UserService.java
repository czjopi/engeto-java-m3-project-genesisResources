package com.github.czjopi.genesisResources.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.czjopi.genesisResources.exception.DuplicatePersonIdException;
import com.github.czjopi.genesisResources.exception.UserNotFoundException;
import com.github.czjopi.genesisResources.mapper.UserMapper;
import com.github.czjopi.genesisResources.model.User;
import com.github.czjopi.genesisResources.model.UserCreateDto;
import com.github.czjopi.genesisResources.model.UserDetailDto;
import com.github.czjopi.genesisResources.model.UserShortDto;
import com.github.czjopi.genesisResources.model.UserUpdateDto;
import com.github.czjopi.genesisResources.repository.UserRepository;
import jakarta.transaction.Transactional;

/**
 * Service class for managing users and user-related operations. Provides methods for CRUD
 * operations and user data transformation.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users in short DTO format.
     * 
     * @return list of UserShortDto representing all users
     */
    public List<UserShortDto> getAllUsersShort() {
        log.debug("Fetching all users in short format");
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false).map(UserMapper::toUserShortDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all users in detail DTO format.
     * 
     * @return list of UserDetailDto representing all users
     */
    public List<UserDetailDto> getAllUsersDetail() {
        log.debug("Fetching all users in detail format");
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false).map(UserMapper::toUserDetailDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by ID in short DTO format.
     * 
     * @param id user ID
     * @return Optional containing UserShortDto if found, otherwise empty
     */
    public Optional<UserShortDto> getUserByIdShort(int id) {
        log.debug("Fetching user by ID {} in short format", id);
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(UserMapper::toUserShortDto);
    }

    /**
     * Retrieves a user by ID in detail DTO format.
     * 
     * @param id user ID
     * @return Optional containing UserDetailDto if found, otherwise empty
     */
    public Optional<UserDetailDto> getUserByIdDetail(int id) {
        log.debug("Fetching user by ID {} in detail format", id);
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(UserMapper::toUserDetailDto);
    }

    /**
     * Creates a new user from the given DTO.
     * 
     * @param user UserCreateDto with user data
     * @return UserDetailDto of the created user
     * @throws DuplicatePersonIdException if personId already exists
     */
    @Transactional
    public UserDetailDto createUser(UserCreateDto user) {
        log.debug("Creating new user");
        UUID uuid = UUID.randomUUID();

        Optional<User> existingUser = userRepository.findByPersonId(user.getPersonId());
        if (existingUser.isPresent()) {
            log.error("User with the same personID already exists");
            throw new DuplicatePersonIdException("User with the same personID already exists.");
        }

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPersonId(user.getPersonId());
        newUser.setUuid(uuid.toString());

        User saved = userRepository.save(newUser);
        log.info("Created new user with ID {}", saved.getId());
        return UserMapper.toUserDetailDto(saved);
    }

    /**
     * Updates an existing user with the given data.
     * 
     * @param user UserUpdateDto with updated data
     * @return UserDetailDto of the updated user
     * @throws UserNotFoundException if user is not found
     */
    @Transactional
    public UserDetailDto updateUser(UserUpdateDto user) {
        log.debug("Updating user with ID {}", user.getId());
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> {
            log.error("User with ID {} not found", user.getId());
            return new UserNotFoundException("User with ID " + user.getId() + " not found.");
        });

        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        User saved = userRepository.save(existingUser);
        log.info("Updated user with ID {}", saved.getId());
        return UserMapper.toUserDetailDto(saved);
    }

    /**
     * Deletes a user by ID.
     * 
     * @param id user ID
     * @return true if user was deleted
     * @throws UserNotFoundException if user is not found
     */
    @Transactional
    public boolean deleteUserById(int id) {
        log.debug("Deleting user with ID {}", id);
        userRepository.findById(id).orElseThrow(() -> {
            log.error("User with ID {} not found", id);
            return new UserNotFoundException("User with ID " + id + " not found.");
        });
        userRepository.deleteById(id);
        log.info("Deleted user with ID {}", id);
        return true;
    }
}
