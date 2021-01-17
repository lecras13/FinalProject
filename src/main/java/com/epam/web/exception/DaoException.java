package com.epam.web.exception;

public class DaoException extends Exception {
    public DaoException() {super();}

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }
}
