package com.epam.internetprovider.validator;

/**
 * The {@code Validator<T>} interface represents object validation.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface Validator<T> {

    /**
     * Check object for valid.
     *
     * @param object the current object
     * @return the boolean
     */
    boolean isValid(T object);
}
