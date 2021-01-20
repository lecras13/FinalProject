package com.epam.web.service;

import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code UserService} represents operations with {@link User}.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface UserService {

    /**
     * Login operation
     *
     * @param login    login for search
     * @param password the password
     * @return {@link User} if it exists
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     * Get all users
     *
     * @return list users
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Save new user
     *
     * @param user {@link User} for save
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    void saveUser(User user) throws ServiceException;

    /**
     * Changing user password
     *
     * @param id       id user
     * @param password the new password
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    void changePassword(Long id, String password) throws ServiceException;

    /**
     * Updating user by admin
     *
     * @param params parameters for updating user
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    void updateUserByAdmin(Object[] params) throws ServiceException;

    /**
     * Updating user by user
     *
     * @param params parameters for updating user
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    void updateUserByUser(Object[] params) throws ServiceException;

    /**
     * Search user by id
     *
     * @param id id user to search
     * @return {@link User} if it exists
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    Optional<User> getUserById(Long id) throws ServiceException;
}
