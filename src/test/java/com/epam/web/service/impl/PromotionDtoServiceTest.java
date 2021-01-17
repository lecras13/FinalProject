package com.epam.web.service.impl;

import com.epam.web.dao.PaymentDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.Payment;
import com.epam.web.entity.dto.PromotionDto;
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

public class PromotionDtoServiceTest {
   /* private static final PromotionDto FIRST_PAYMENT = new PromotionDto(5L, "SUPER", new Date(), new Date());
    private static final PromotionDto SECOND_PAYMENT = new PromotionDto(6L, "SUPER+", new Date(),  new Date());
    private static final PromotionDto THIRD_PAYMENT = new PromotionDto(7L, "SUPER++", new Date(),  new Date());
    private static final int FIRST_ROW = 1;
    private static final int ROW_COUNT = 2;
    private static final List<Payment> PAYMENTS = Arrays.asList(FIRST_PAYMENT, SECOND_PAYMENT, THIRD_PAYMENT);
    private static final List<Payment> PAYMENTS_FOR_PAGE = Arrays.asList(FIRST_PAYMENT, SECOND_PAYMENT);

    private DaoHelperFactory helperFactoryMock;
    private DaoHelper helperMock;
    private PaymentDao paymentDaoMock;
    private PaymentValidator paymentValidator;
    private PaymentService paymentService;

    @Before
    public void setUp(){
        helperFactoryMock = Mockito.mock(DaoHelperFactory.class);
        helperMock = Mockito.mock(DaoHelper.class);
        paymentDaoMock = Mockito.mock(PaymentDao.class);
        paymentValidator = Mockito.mock(PaymentValidator.class);
        paymentService = new PaymentServiceImpl(helperFactoryMock, paymentValidator);
    }

    @Test
    public void testFindPaymentForPageShouldReturnPaymentsByUserId() throws ServiceException, DaoException{
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createPaymentDao()).thenReturn(paymentDaoMock);
        when(paymentDaoMock.getPaymentsByUserIdForPage(ID, FIRST_ROW, ROW_COUNT)).thenReturn(PAYMENTS_FOR_PAGE);

        List<Payment> actual = paymentService.getPaymentsForPage(ID, FIRST_ROW, ROW_COUNT);

        Assert.assertEquals(actual, PAYMENTS_FOR_PAGE);
    }*/
}
