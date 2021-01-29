package com.epam.internetprovider.dao;

import com.epam.internetprovider.entity.Payment;
import com.epam.internetprovider.exception.DaoException;

import java.util.List;

/**
 * The {@code PaymentDao} presents implementation {@link Payment} data access object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface PaymentDao extends DaoPersistent<Payment> {

    /**
     * Get the payments list by user id
     *
     * @param id the current user id
     * @return found {@link List} of payments for current user
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<Payment> getPaymentsByUserId(Long id) throws DaoException;

    /**
     * Get the payments list by user id for page
     *
     * @param id the current user id
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of payments for current user for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<Payment> getPaymentsByUserIdForPage(Long id, int firstRow, int rowCount) throws DaoException;
}

