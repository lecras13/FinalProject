package com.epam.web.service.impl;

import com.epam.web.dao.PaymentDao;

import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.Payment;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PaymentService;
import com.epam.web.validator.PaymentValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

public class PaymentServiceImplTest {
    private static final Long ID = 1L;
    private static final Payment FIRST_PAYMENT = new Payment(5L, 5D, new Date(), 1L);
    private static final Payment SECOND_PAYMENT = new Payment(6L, 5D, new Date(), 1L);
    private static final Payment THIRD_PAYMENT = new Payment(7L, 5D, new Date(), 1L);
    private static final int FIRST_ROW = 1;
    private static final int ROW_COUNT = 2;
    private static final List<Payment> PAYMENTS = Arrays.asList(FIRST_PAYMENT, SECOND_PAYMENT, THIRD_PAYMENT);
    private static final List<Payment> PAYMENTS_FOR_PAGE = Arrays.asList(FIRST_PAYMENT, SECOND_PAYMENT);

    private DaoHelperFactory helperFactoryMock;
    private DaoHelper helperMock;
    private PaymentDao paymentDaoMock;
    private PaymentService paymentService;

    @Before
    public void setUp(){
        helperFactoryMock = Mockito.mock(DaoHelperFactory.class);
        helperMock = Mockito.mock(DaoHelper.class);
        paymentDaoMock = Mockito.mock(PaymentDao.class);
        PaymentValidator paymentValidatorMock = Mockito.mock(PaymentValidator.class);
        paymentService = new PaymentServiceImpl(helperFactoryMock, paymentValidatorMock);
    }

    @Test
    public void testFindPaymentShouldReturnPaymentByUserId() throws ServiceException, DaoException{
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createPaymentDao()).thenReturn(paymentDaoMock);
        when(paymentDaoMock.getPaymentsByUserId(ID)).thenReturn(PAYMENTS);

        List<Payment> actual = paymentService.getPayments(ID);

        Assert.assertEquals(actual, PAYMENTS);
    }

    @Test(expected = ServiceException.class)
    public void testFindPaymentByUserIdShouldReturnServiceException() throws ServiceException, DaoException{
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createPaymentDao()).thenReturn(paymentDaoMock);
        when(paymentDaoMock.getPaymentsByUserId(ID)).thenThrow(DaoException.class);

        List<Payment> actual = paymentService.getPayments(ID);
    }

    @Test(expected = ServiceException.class)
    public void testFindPaymentForPageShouldReturnServiceException() throws ServiceException, DaoException{
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createPaymentDao()).thenReturn(paymentDaoMock);
        when(paymentDaoMock.getPaymentsByUserIdForPage(ID, FIRST_ROW, ROW_COUNT)).thenThrow(DaoException.class);

        List<Payment> actual = paymentService.getPaymentsForPage(ID, FIRST_ROW, ROW_COUNT);
    }

    @Test
    public void testFindPaymentForPageShouldReturnPaymentsByUserId() throws ServiceException, DaoException{
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createPaymentDao()).thenReturn(paymentDaoMock);
        when(paymentDaoMock.getPaymentsByUserIdForPage(ID, FIRST_ROW, ROW_COUNT)).thenReturn(PAYMENTS_FOR_PAGE);

        List<Payment> actual = paymentService.getPaymentsForPage(ID, FIRST_ROW, ROW_COUNT);

        Assert.assertEquals(actual, PAYMENTS_FOR_PAGE);
    }
}
