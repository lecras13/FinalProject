package com.epam.internetprovider.dao;

import com.epam.internetprovider.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code DaoPersistent<T>} presents basic Data access object interface
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface DaoPersistent<T> {

    /**
     * Get the persistent object by id
     *
     * @param id the current id
     * @return found {@link Optional} persistent object
     */
    Optional<T> getById(Long id) throws DaoException;

    /**
     * Getting {@link List} of persistent objects
     *
     * @return found {@link List} of persistent object
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<T> getAll() throws DaoException;

    /**
     * Save or update the persistent object
     *
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    void save(T item) throws DaoException;

    /**
     * Delete the  persistent object by id
     *
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    void removeById(Long id) throws DaoException;
}
