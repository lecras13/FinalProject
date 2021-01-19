package com.epam.web.exception;

/**
 * The {@code ServiceException} represents exception in service.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class ServiceException extends Exception {

    /**
     * Instantiates a new ServiceException.
     */
    public ServiceException(){
        super();
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param message the throwable
     */
    public ServiceException(String message){
        super(message);
    }

    /**
     * Instantiates a new ServiceException.
     *
     * @param cause the throwable
     */
    public ServiceException(Throwable cause){
        super(cause);
    }
}
