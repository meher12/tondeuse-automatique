package com.mowitnow.exception;

/**
 * Exception lancée lorsqu'une tondeuse tente de sortir de la pelouse.
 */
public class PositionOutOfBoundsException extends RuntimeException {
    public PositionOutOfBoundsException(String message) {
        super(message);
    }
}
