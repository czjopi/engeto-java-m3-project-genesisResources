package com.github.czjopi.genesisResources.model;

/**
 * Data Transfer Object for error responses.
 */
public class ErrorResponseDto {
    private String message;

    public ErrorResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
