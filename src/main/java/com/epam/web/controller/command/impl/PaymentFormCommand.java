package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentFormCommand implements Command {
    private static final String PAYMENT_FORM_LOCATION = "/WEB-INF/view/pages/payment.jsp";

    public PaymentFormCommand() {
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {
        return CommandResult.forward(PAYMENT_FORM_LOCATION);
    }
}
