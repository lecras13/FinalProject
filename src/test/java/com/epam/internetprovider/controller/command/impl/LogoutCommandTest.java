package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;
import com.epam.internetprovider.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LogoutCommandTest {
    private static final String INDEX_PAGE = "index.jsp";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp(){
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
    }

    @Test
    public void testLogoutShouldReturnForwardToIndexPage() throws ServiceException {
        //given
        //when
        Command command = new LogoutCommand();
        when(request.getParameter(anyString())).thenReturn("");
        when(request.getSession()).thenReturn(session);
        CommandResult expected = command.execute(request, response);
        //then
        CommandResult actual = CommandResult.forward(INDEX_PAGE);
        Assert.assertEquals(expected, actual);
    }
}
