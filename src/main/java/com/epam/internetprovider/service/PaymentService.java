package com.epam.internetprovider.service;

import com.epam.internetprovider.entity.Payment;
import com.epam.internetprovider.exception.ServiceException;

import java.util.List;

/**
 * The {@code PaymentService} presents operations {@link Payment}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface PaymentService {

    /**
     * Get the payments list by user id
     *
     * @param id the current user id
     * @return found {@link List} of payments for current user
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<Payment> getPayments(Long id) throws ServiceException;

    /**
     * Get the payments list by user id for page
     *
     * @param id the current user id
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of payments for current user for page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<Payment> getPaymentsForPage(Long id, int firstRow, int rowCount) throws ServiceException;

    /**
     * Add new payment and increase balance user
     *
     * @param amount the current amount to add
     * @param userId user id to increase his amount
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    void pay(Double amount, Long userId) throws ServiceException;
}
