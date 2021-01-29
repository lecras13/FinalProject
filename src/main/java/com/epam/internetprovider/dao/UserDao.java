package com.epam.internetprovider.dao;

import com.epam.internetprovider.entity.User;
import com.epam.internetprovider.exception.DaoException;

import java.util.Optional;

/**
 * The {@code UserDao} presents implementation {@link User} data access object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface UserDao extends DaoPersistent<User> {

    /**
     * Get the Optional<User>
     *
     * @param login    login for search
     * @param password the password
     * @return {@link User} if it exists
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Changing user password
     *
     * @param id       id user
     * @param password the new password
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    void changePasswordByUserId(Long id, String password) throws DaoException;

    /**
     * Updating user by admin
     *
     * @param params parameters for updating user
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    void updateUserByAdmin(Object[] params) throws DaoException;

    /**
     * Updating user by user
     *
     * @param params parameters for updating user
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    void updateUserByUser(Object[] params) throws DaoException;

    /**
     * Increase balance user
     *
     * @param id     id user
     * @param amount amount to increase balance
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    void updateAmount(Long id, Double amount) throws DaoException;
}
