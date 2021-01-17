package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.Payment;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PaymentService;
import com.epam.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class PayCommand implements Command {
    private static final String INFO_LOCATION = "/controller?command=info";
    private static final String PAYMENT_FORM_LOCATION = "/WEB-INF/view/pages/payment.jsp";
    private static final String PAYMENTS = "payments";
    private static final String ID = "user_id";
    private static final String AMOUNT = "amount";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessageData";
    private static final String ERROR_MESSAGE = "Payment did not allowed!";

    private final PaymentService paymentService;

    public PayCommand(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(ID);
        Double amount = Double.parseDouble(request.getParameter(AMOUNT));
        try {
            paymentService.pay(amount, userId);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            return CommandResult.forward(PAYMENT_FORM_LOCATION);
        }
        List<Payment> payments = paymentService.getPayments(userId);
        request.setAttribute(PAYMENTS, payments);
        return CommandResult.redirect(INFO_LOCATION);
    }
}