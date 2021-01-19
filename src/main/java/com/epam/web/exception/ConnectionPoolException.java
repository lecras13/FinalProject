package com.epam.web.exception;

/**
 * The {@code ConnectionPoolException} represents connection pool exception.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class ConnectionPoolException extends RuntimeException {

    /**
     * Instantiates a new ConnectionPoolException.
     */
    public ConnectionPoolException(){
        super();
    }

    /**
     * Instantiates a new ConnectionPoolException.
     *
     * @param message   the throwable message
     * @param cause the throwable
     */
    public ConnectionPoolException(String message, Throwable cause){
        super(message, cause);
    }

}
