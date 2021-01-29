package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.CommandResult;
import com.epam.internetprovider.controller.command.PageController;
import com.epam.internetprovider.entity.Payment;
import com.epam.internetprovider.entity.Role;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.PaymentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PaymentHistoryCommandTest {
    private static final String CURRENT_PAGE = "currentPage";
    private static final Payment FIRST_PAYMENT = new Payment(5L, 5D, Date.valueOf("2021-01-15"), 1L);
    private static final Payment SECOND_PAYMENT = new Payment(6L, 5D, Date.valueOf("2021-01-15"), 1L);
    private static final Payment THIRD_PAYMENT = new Payment(7L, 5D, Date.valueOf("2021-01-15"), 1L);
    private static final List<Payment> PAYMENTS_LIST = Arrays.asList(FIRST_PAYMENT, SECOND_PAYMENT, THIRD_PAYMENT);
    private static final String ID = "user_id";
    private static final String SESSION_ROLE = "userRole";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String PAYMENTS = "payments";
    private static final String PAYMENTS_PAGE = "/WEB-INF/view/pages/payments.jsp";

    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private HttpSession sessionMock;
    private PaymentService paymentServiceMock;
    private PageController pageControllerMock;
    private PaymentHistoryCommand paymentHistoryCommand;

    @Before
    public void setUp() {
        requestMock = Mockito.mock(HttpServletRequest.class);
        responseMock = Mockito.mock(HttpServletResponse.class);
        sessionMock = Mockito.mock(HttpSession.class);
        paymentServiceMock = Mockito.mock(PaymentService.class);
        pageControllerMock = Mockito.mock(PageController.class);
        paymentHistoryCommand = new PaymentHistoryCommand(paymentServiceMock, pageControllerMock);
    }

    @Test
    public void testPaymentsHistoryShouldCheckValidCurrentPage() throws ServiceException {
        //given
        //when
        setValidAttributes();
        paymentHistoryCommand.execute(requestMock, responseMock);
        //then
        verify(requestMock, times(1)).setAttribute(CURRENT_PAGE, 1);
    }

    @Test
    public void testPaymentHistoryShouldCheckValidNoOfPage() throws ServiceException {
        //given
        //when
        setValidAttributes();
        paymentHistoryCommand.execute(requestMock, responseMock);
        //then
        verify(requestMock, times(1)).setAttribute(NO_OF_PAGES, 1);
    }

    @Test
    public void testPaymentHistoryShouldValidList() throws ServiceException {
        //given
        //when
        setValidAttributes();
        paymentHistoryCommand.execute(requestMock, responseMock);
        //then
        verify(requestMock, times(1)).setAttribute(PAYMENTS, PAYMENTS_LIST);
    }

    @Test
    public void testPaymentHistoryShouldForwardToPayments() throws ServiceException {
        //given
        //when
        setValidAttributes();
        CommandResult expected = paymentHistoryCommand.execute(requestMock, responseMock);
        //then
        CommandResult actual = CommandResult.forward(PAYMENTS_PAGE);
        Assert.assertEquals(expected, actual);
    }


    private void setValidAttributes() throws ServiceException {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute(ID)).thenReturn(1L);
        when(sessionMock.getAttribute(SESSION_ROLE)).thenReturn(Role.USER);
        when(pageControllerMock.getCurrentPage(requestMock)).thenReturn(1);
        when(paymentServiceMock.getPaymentsForPage(anyLong(), anyInt(), anyInt())).thenReturn(PAYMENTS_LIST);
        when(paymentServiceMock.getPayments(anyLong())).thenReturn(PAYMENTS_LIST);
        when(pageControllerMock.getNumberPages(anyInt(), anyInt())).thenReturn(1);
    }
}
