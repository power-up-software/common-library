package com.powerup.common.api;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The <code>NotFoundException</code> class represents the case where the requested endpoint is not found. This could be a result of an invalid id,
 * use of a deprecated URL, etc.
 *
 * @author Chris Picard
 */
@SuppressWarnings("unused")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RequestException {
    /**
     * Serial Version ID implements the {@link java.io.Serializable} contract.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Base constructor taking an error message.
     *
     * @param message Error message describing what caused the exception.
     */
    public NotFoundException(final String message) {
        super(message);
    }

    /**
     * Base constructor taking an error message.
     *
     * @param message Error message describing what caused the exception.
     * @param cause Exception that caused this exception to be generated.
     */
    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}