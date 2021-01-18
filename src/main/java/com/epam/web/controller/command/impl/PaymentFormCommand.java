package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code PaymentCommand} the class represents transition to a payment page.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PaymentFormCommand implements Command {
    private static final String PAYMENT_FORM_LOCATION = "/WEB-INF/view/pages/payment.jsp";

    public PaymentFormCommand() {
    }

    /**
     * Execute command to payment form using the parameters of HttpServletRequest object
     * and returns CommandResult to a payment page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return CommandResult.forward(PAYMENT_FORM_LOCATION);
    }
}
