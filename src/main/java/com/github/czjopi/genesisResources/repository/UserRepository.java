package com.github.czjopi.genesisResources.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.github.czjopi.genesisResources.model.User;

/**
 * Repository interface for User entity operations.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByPersonId(String personId);

}
