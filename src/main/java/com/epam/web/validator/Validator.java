package com.epam.web.validator;

public interface Validator<T> {
    boolean isValid(T object);
}
