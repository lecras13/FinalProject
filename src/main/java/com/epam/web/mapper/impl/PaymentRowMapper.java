package com.epam.web.mapper.impl;

import com.epam.web.entity.Payment;
import com.epam.web.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PaymentRowMapper implements RowMapper<Payment> {
    private static final String ID = "id";
    private static final String AMOUNT = "amount";
    private static final String PAYMENT_DATE = "payment_date";
    private static final String USER_ID = "user_id";

    @Override
    public Payment map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Double amount = resultSet.getDouble(AMOUNT);
        Date paymentDate = resultSet.getDate(PAYMENT_DATE);
        Long userId = resultSet.getLong(USER_ID);
        return new Payment(id, amount, paymentDate, userId);
    }
}