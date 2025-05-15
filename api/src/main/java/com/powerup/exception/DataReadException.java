/*
 * Copyright (c) Power Up Software Engineering LLC 2025.
 * All Rights Reserved
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES,
 * INCLUDING LOST PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF REGENTS HAS BEEN ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED HEREUNDER IS PROVIDED
 * "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 */

package com.powerup.exception;

import java.io.Serial;

/**
 * A <code>DataCreationException</code> represents an error in creating data content.
 * Depending on the implementation of the data service, this could be a web error, file IO, etc.
 *
 * @author Chris Picard
 */
public class DataReadException extends Exception {
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
    public DataReadException(final String message) {
        super(message);
    }

    /**
     * Base constructor taking an error message.
     *
     * @param message Error message describing what caused the exception.
     * @param cause Exception that caused this exception to be generated.
     */
    public DataReadException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
