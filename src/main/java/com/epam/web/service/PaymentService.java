package com.epam.web.service;

import com.epam.web.entity.Payment;
import com.epam.web.exception.ServiceException;

import java.util.List;

public interface PaymentService {
    List<Payment> getPayments(Long id) throws ServiceException;

    List<Payment> getPaymentsForPage(Long id, int firstRow, int rowCount) throws ServiceException;

    void pay(Double amount, Long userId) throws ServiceException;
}
