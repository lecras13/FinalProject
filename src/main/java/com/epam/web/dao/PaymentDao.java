package com.epam.web.dao;

import com.epam.web.entity.Payment;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface PaymentDao extends DaoPersistent<Payment> {
    List<Payment> getPaymentsByUserId(Long id) throws DaoException;

    List<Payment> getPaymentsByUserIdForPage(Long id, int firstRow, int rowCount) throws DaoException;
}

