package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    private static final User ADMIN = new User(1L, "admin", "admin", "Роман", "Александров", Role.ADMIN, 0.0D, false, 0.0D, null, null, 0);
    private static final User USER = new User(2L, "user1", "user1", "Дмитрий", "Смирнов", Role.USER, 0.0D, false, 0.0D, null, null, 0);
    private static final String USERS_PAGE = "/controller?command=users";
    private static final String INFO_PAGE = "/controller?command=info";
    private static final String INDEX_PAGE = "index.jsp";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserService userService;
    private LoginCommand loginCommand;

    @Before
    public void setUp(){
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        userService = Mockito.mock(UserService.class);
        loginCommand = new LoginCommand(userService);
    }

    @Test
    public void testLoginShouldRedirectToUsersPage() throws ServiceException{
        //given
        //when
        when(request.getParameter(anyString())).thenReturn("");
        when(userService.login(anyString(), anyString())).thenReturn(Optional.of(ADMIN));
        when(request.getSession()).thenReturn(session);
        CommandResult expected = loginCommand.execute(request, response);
        //then
        CommandResult actual = CommandResult.redirect(USERS_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLoginShouldForwardToIndexPage() throws ServiceException{
        //given
        //when
        when(request.getParameter(anyString())).thenReturn("");
        when(userService.login(anyString(), anyString())).thenReturn(Optional.empty());
        CommandResult expected = loginCommand.execute(request, response);
        //then
        CommandResult actual = CommandResult.redirect(INDEX_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLoginShouldForwardToInfoPage() throws ServiceException{
        //given
        //when
        when(request.getParameter(anyString())).thenReturn("");
        when(userService.login(anyString(), anyString())).thenReturn(Optional.of(USER));
        when(request.getSession()).thenReturn(session);
        CommandResult expected = loginCommand.execute(request, response);
        //then
        CommandResult actual = CommandResult.redirect(INFO_PAGE);
        Assert.assertEquals(expected, actual);
    }
}
