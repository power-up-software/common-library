package com.powerup.common.api;

import java.io.Serial;

/**
 * The <code>RequestException</code> class represents any exception that occurred when processing a web request.
 *
 * @author Chris Picard
 */
@SuppressWarnings("unused")
public class RequestException extends RuntimeException {
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
    public RequestException(final String message) {
        super(message);
    }

    /**
     * Base constructor taking an error message.
     *
     * @param message Error message describing what caused the exception.
     * @param cause Exception that caused this exception to be generated.
     */
    public RequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

}