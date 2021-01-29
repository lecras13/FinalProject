package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;
import com.epam.internetprovider.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;

public class PaymentFormCommandTest {
    private static final String PAYMENT_FORM_LOCATION = "/WEB-INF/view/pages/payment.jsp";

    @Test
    public void testGoToPageShouldForwardToSpecifiedPage() throws ServiceException {
        //given
        Command command = new PaymentFormCommand();
        CommandResult expected = CommandResult.forward(PAYMENT_FORM_LOCATION);
        //when
        CommandResult actual = command.execute(null, null);
        //then
        Assert.assertEquals(actual, expected);
    }
}
