package com.epam.web.dao;

import com.epam.web.exception.DaoException;

import java.util.Map;
import java.util.Optional;

/**
 * The {@code Dao<T>} presents basic one method Data access object interface.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface Dao<T> {

    /**
     * Executing query for single result
     *
     * @param query the current query to execute
     * @param params the current params to build query
     */
    Optional<T> executeForSingleResult(String query, Object... params) throws DaoException;
}
