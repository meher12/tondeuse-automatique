package com.mowitnow.exception;

/**
 * Exception lancée lorsqu'une instruction invalide est rencontrée.
 */
public class InvalidInstructionException extends RuntimeException {
    public InvalidInstructionException(String message) {
        super(message);
    }
}
