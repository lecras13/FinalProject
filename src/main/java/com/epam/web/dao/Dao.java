package com.epam.web.dao;

import com.epam.web.exception.DaoException;

import java.util.Optional;

public interface Dao<T> {
    Optional<T> executeForSingleResult(String query, Object... params) throws DaoException;
}
