package com.epam.web.exception;

/**
 * The {@code DaoException} represents exception with dao.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class DaoException extends Exception {

    /**
     * Instantiates a new DaoException.
     *
     */
    public DaoException() {super();}

    /**
     * Instantiates a new DaoException.
     *
     * @param cause the throwable
     */
    public DaoException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new DaoException.
     *
     * @param message   the throwable message
     */
    public DaoException(String message) {
        super(message);
    }
}
