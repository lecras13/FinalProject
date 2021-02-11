package com.epam.internetprovider.dao.impl;

import com.epam.internetprovider.dao.AbstractDaoPersistent;
import com.epam.internetprovider.dao.UserDao;
import com.epam.internetprovider.entity.User;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.extractor.impl.UserExtractor;
import com.epam.internetprovider.mapper.impl.UserRowMapper;

import java.sql.Connection;
import java.util.Optional;

/**
 * The {@code UserDaoImpl} presents user dao implementation
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserDaoImpl extends AbstractDaoPersistent<User> implements UserDao {
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from users where login=? and password=SHA1(?)";
    private static final String TABLE_NAME = "users";
    private static final String UPDATE = "UPDATE users SET login=?, password=SHA1(?), first_name=?, last_name=?," +
            " role=?, total_amount=?, status_block=?, traffic=?, discount=?, tariff_id=?, promotion_id=? where id=?";
    private static final String SAVE = "INSERT INTO users(login, password, first_name, last_name, total_amount," +
            " traffic, tariff_id, promotion_id, id) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password=SHA1(?) where id=?";
    private static final String UPDATE_USER_BY_ADMIN = "UPDATE users SET login=?, first_name=?, last_name=?, " +
            "status_block=?, discount=?, tariff_id=? where id=?";
    private static final String UPDATE_USER_BY_USER = "UPDATE users SET login=?, first_name=?, last_name=?," +
            " tariff_id=? where id=?";
    private static final String UPDATE_USER_AMOUNT = "UPDATE users SET total_amount=? where id=?";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), new UserExtractor(), TABLE_NAME, SAVE, UPDATE);
    }

    /**
     * Get the Optional<User>
     *
     * @param login    login for search
     * @param password the password
     * @return {@link User} if it exists
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    /**
     * Changing user password
     *
     * @param id       id user
     * @param password the new password
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public void changePasswordByUserId(Long id, String password) throws DaoException {
        saveParametersItem(UPDATE_USER_PASSWORD, password, id);
    }

    /**
     * Updating user by admin
     *
     * @param params parameters for updating user
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public void updateUserByAdmin(Object[] params) throws DaoException {
        saveParametersItem(UPDATE_USER_BY_ADMIN, params);
    }

    /**
     * Updating user by user
     *
     * @param params parameters for updating user
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public void updateUserByUser(Object[] params) throws DaoException {
        saveParametersItem(UPDATE_USER_BY_USER, params);
    }

    /**
     * Increase balance user
     *
     * @param id     id user
     * @param amount amount to increase balance
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public void updateAmount(Long id, Double amount) throws DaoException {
        saveParametersItem(UPDATE_USER_AMOUNT, amount, id);
    }
}

