package com.github.czjopi.genesisResources.exception;

/**
 * Exception thrown when a duplicate person ID is encountered.
 */
public class DuplicatePersonIdException extends RuntimeException {
    public DuplicatePersonIdException(String message) {
        super(message);
    }

}
