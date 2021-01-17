package com.epam.web.controller.command;

import com.epam.web.exception.ServiceException;
import org.junit.Assert;
import org.junit.Test;

public class GoToPageTest {
    private static final String MAIN_LOCATION = "WEB-INF/view/pages/main.jsp";

    @Test
    public void testGoToPageShouldForwardToSpecifiedPage() throws ServiceException {
        //given
        Command command = new GoToPage(MAIN_LOCATION);
        CommandResult expected = CommandResult.forward(MAIN_LOCATION);
        //when
        CommandResult actual = command.execute(null, null);
        //then
        Assert.assertEquals(actual, expected);
    }
}
