package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDaoPersistent;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.extractor.impl.UserExtractor;
import com.epam.web.mapper.impl.UserRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDaoPersistent<User> implements UserDao {
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from users where login=? and password=SHA1(?)";
    private static final String TABLE_NAME = "users";
    private static final String GET_USERS_FOR_TABLE = "SELECT * FROM users limit ?, ?";
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

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public void changePasswordByUserId(Long id, String password) throws DaoException {
        saveParametersItem(UPDATE_USER_PASSWORD, password, id);
    }

    @Override
    public void updateUserByAdmin(Object[] params) throws DaoException {
        saveParametersItem(UPDATE_USER_BY_ADMIN, params);
    }

    @Override
    public void updateUserByUser(Object[] params) throws DaoException {
        saveParametersItem(UPDATE_USER_BY_USER, params);
    }

    @Override
    public void updateAmount(Long id, Double amount) throws DaoException {
        saveParametersItem(UPDATE_USER_AMOUNT, amount, id);
    }
}

