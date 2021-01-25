package com.epam.web.service.impl;

import com.epam.web.dao.PaymentDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.Payment;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PaymentService;
import com.epam.web.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The {@code PaymentServiceImpl} presents implementation {@link PaymentService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PaymentServiceImpl implements PaymentService {
    private final DaoHelperFactory daoHelperFactory;
    private final Validator<Payment> paymentValidator;

    public PaymentServiceImpl(DaoHelperFactory daoHelperFactory, Validator<Payment> paymentValidator){
        this.daoHelperFactory = daoHelperFactory;
        this.paymentValidator = paymentValidator;
    }

    @Override
    public List<Payment> getPayments(Long id) throws ServiceException{
        try (DaoHelper factory = daoHelperFactory.create()) {
            PaymentDao dao = factory.createPaymentDao();
            return dao.getPaymentsByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Payment> getPaymentsForPage(Long id, int firstRow, int rowCount) throws ServiceException{
        try (DaoHelper factory = daoHelperFactory.create()) {
            PaymentDao dao = factory.createPaymentDao();
            return dao.getPaymentsByUserIdForPage(id, firstRow, rowCount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void pay(Double amountToPay, Long userId) throws ServiceException{
        DaoHelper factory = null;
        try {
            factory = daoHelperFactory.create();
            factory.startTransAction();
            PaymentDao paymentDao = factory.createPaymentDao();
            Payment payment = new Payment(null, amountToPay, new Date(), userId);
            if (!paymentValidator.isValid(payment)) {
                throw new ServiceException("Not valid payment!");
            }
            paymentDao.save(payment);

            UserDao userDao = factory.createUserDao();
            Optional<User> optionalUser = userDao.getById(userId);
            User user = optionalUser.get();
            Double amountToAdd = payment.getAmount();
            Double amount = user.getTotalAmount();
            Double newAmount = amountToAdd + amount;
            userDao.updateAmount(userId, newAmount);
            factory.endTransaction();
        } catch (DaoException e) {
            factory.rollback();
            throw new ServiceException(e);
        }
    }
}
