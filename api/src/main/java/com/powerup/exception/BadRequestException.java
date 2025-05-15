/*
 * Copyright (c) Power Up Software Engineering LLC 2025.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.powerup.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The <code>BadRequestException</code> class represents a request that is not formatted correctly. As a result the request was rejected by the
 * system.
 *
 * @author Chris Picard
 */
@SuppressWarnings("unused")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RequestException {
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
    public BadRequestException(final String message) {
        super(message);
    }

    /**
     * Base constructor taking an error message.
     *
     * @param message Error message describing what caused the exception.
     * @param cause Exception that caused this exception to be generated.
     */
    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
