package com.powerup.common.api;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The <code>InternalServerErrorException</code> class represents an internal error occurring with the server that caused the request to fail. This is
 * typically either a programing error or a temporal issues such as a dependent service being unavailable.
 *
 * @author Chris Picard
 */
@SuppressWarnings("unused")
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RequestException {
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
    public InternalServerErrorException(final String message) {
        super(message);
    }

    /**
     * Base constructor taking an error message.
     *
     * @param message Error message describing what caused the exception.
     * @param cause Exception that caused this exception to be generated.
     */
    public InternalServerErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }
}