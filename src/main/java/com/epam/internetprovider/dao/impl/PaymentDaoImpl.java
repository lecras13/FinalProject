package com.epam.internetprovider.dao.impl;

import com.epam.internetprovider.dao.AbstractDaoPersistent;
import com.epam.internetprovider.dao.PaymentDao;
import com.epam.internetprovider.entity.Payment;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.extractor.impl.PaymentExtractor;
import com.epam.internetprovider.mapper.impl.PaymentRowMapper;

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

    /**
     * Get the payments list by user id
     *
     * @param id the current user id
     * @return found {@link List} of payments for current user
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    public List<Payment> getPaymentsByUserId(Long id) throws DaoException {
        return executeQuery(GET_PAYMENTS_BY_USER_ID, id);
    }

    /**
     * Get the payments list by user id for page
     *
     * @param id the current user id
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of payments for current user for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<Payment> getPaymentsByUserIdForPage(Long id, int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_PAYMENTS_BY_USER_ID_FOR_PAGE, id, firstRow, rowCount);
    }
}

