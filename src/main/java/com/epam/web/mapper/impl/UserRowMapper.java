package com.epam.web.mapper.impl;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String ROLE = "role";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String STATUS_BLOCK = "status_block";
    private static final String TRAFFIC = "traffic";
    private static final String DISCOUNT = "discount";
    private static final String TARIFF_ID = "tariff_id";
    private static final String PROMOTION_ID = "promotion_id";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        Role role = Role.valueOf(resultSet.getString(ROLE));
        Double totalAmount = resultSet.getDouble(TOTAL_AMOUNT);
        Boolean statusBlock = resultSet.getBoolean(STATUS_BLOCK);
        Double traffic = resultSet.getDouble(TRAFFIC);
        Integer discount = resultSet.getInt(DISCOUNT);
        Long tariffId = resultSet.getLong(TARIFF_ID);
        Long promotionId = resultSet.getLong(PROMOTION_ID);

        return new User(id, login, password, firstName, lastName, role,
                totalAmount, statusBlock, traffic, tariffId, promotionId, discount);
    }
}
