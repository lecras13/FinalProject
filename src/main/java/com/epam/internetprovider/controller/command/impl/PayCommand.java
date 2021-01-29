package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;
import com.epam.internetprovider.entity.Payment;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code PayCommand} the class increase user balance and transition to a appropriate to info page.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PayCommand implements Command {
    private static final String INFO_LOCATION = "/controller?command=info";
    private static final String PAYMENT_FORM_LOCATION = "/WEB-INF/view/pages/payment.jsp";
    private static final String PAYMENTS = "payments";
    private static final String ID = "user_id";
    private static final String AMOUNT = "amount";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessageData";
    private static final String ERROR_MESSAGE = "Payment did not allowed!";

    private final PaymentService paymentService;

    public PayCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Execute command to pay using the parameters of HttpServletRequest object
     * and returns CommandResult to a appropriate to info page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        HttpSession session = servletRequest.getSession();
        Long userId = (Long) session.getAttribute(ID);
        Double amount = Double.parseDouble(servletRequest.getParameter(AMOUNT));
        try {
            paymentService.pay(amount, userId);
        } catch (ServiceException e) {
            servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            return CommandResult.forward(PAYMENT_FORM_LOCATION);
        }
        List<Payment> payments = paymentService.getPayments(userId);
        servletRequest.setAttribute(PAYMENTS, payments);
        return CommandResult.redirect(INFO_LOCATION);
    }
}