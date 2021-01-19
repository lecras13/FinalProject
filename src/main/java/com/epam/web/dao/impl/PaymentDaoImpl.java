package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDaoPersistent;
import com.epam.web.dao.PaymentDao;
import com.epam.web.entity.Payment;
import com.epam.web.exception.DaoException;
import com.epam.web.extractor.impl.PaymentExtractor;
import com.epam.web.mapper.impl.PaymentRowMapper;

import java.sql.Connection;
import java.util.List;

/**
 * The {@code PaymentDaoImpl} presents payment dao implementation
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PaymentDaoImpl extends AbstractDaoPersistent<Payment> implements PaymentDao {
    private static final String TABLE_NAME = "payments";
    private static final String GET_PAYMENTS_BY_USER_ID = "select * from payments where user_id=?";
    private static final String GET_PAYMENTS_BY_USER_ID_FOR_PAGE = "select * from payments where user_id=? order by id desc limit ?, ?";
    private static final String SAVE = "INSERT INTO payments(amount, payment_date, user_id, id) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE promotions SET promotion_name=?, start_date=?, end_date=?, " +
            "description=?, tariff_id=?, new_price=? WHERE id=?";

    public PaymentDaoImpl(Connection connection) {
        super(connection, new PaymentRowMapper(), new PaymentExtractor(), TABLE_NAME, SAVE, UPDATE);
    }

    public List<Payment> getPaymentsByUserId(Long id) throws DaoException {
        return executeQuery(GET_PAYMENTS_BY_USER_ID, id);
    }

    @Override
    public List<Payment> getPaymentsByUserIdForPage(Long id, int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_PAYMENTS_BY_USER_ID_FOR_PAGE, id, firstRow, rowCount);
    }
}

