package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.Promotion;
import com.epam.web.entity.Role;
import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionService;
import com.epam.web.service.TariffPlanService;
import com.epam.web.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class InfoPageCommandTest {
    private static final User USER_VALID = new User(1L, "user", "user", "Роман", "Александров", Role.USER, 0.0D, false, 0.0D, 1L, 5L, 0);
    private static final User USER_INVALID_TARIFF = new User(1L, "user", "user", "Роман", "Александров", Role.USER, 0.0D, false, 0.0D, 1L, 5L, 0);
    private static final User USER_INVALID_PROMOTION = new User(1L, "user", "user", "Роман", "Александров", Role.USER, 0.0D, false, 0.0D, 1L, 1L, 0);
    private static final TariffPlan FIRST_TARIFF = new TariffPlan(1L, "firstTariffName", 1, "firstDescription");
    private static final Promotion PROMOTION = new Promotion(1L, "action", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "description", 1L, 5);
    private static final String INFO_LOCATION = "/WEB-INF/view/pages/info.jsp";
    private static final String SESSION_ROLE = "userRole";
    private static final String ID = "user_id";
    private static final Long ID_LONG = 1L;
    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private HttpSession sessionMock;
    private UserService userServiceMock;
    private TariffPlanService tariffPlanServiceMock;
    private PromotionService promotionServiceMock;
    private InfoPageCommand infoPageCommand;

    @Before
    public void setUp() {
        requestMock = Mockito.mock(HttpServletRequest.class);
        responseMock = Mockito.mock(HttpServletResponse.class);
        sessionMock = Mockito.mock(HttpSession.class);
        tariffPlanServiceMock = Mockito.mock(TariffPlanService.class);
        promotionServiceMock = Mockito.mock(PromotionService.class);
        userServiceMock = Mockito.mock(UserService.class);
        infoPageCommand = new InfoPageCommand(userServiceMock, tariffPlanServiceMock, promotionServiceMock);
    }

    @Test
    public void testShouldForwardToInfoPage() throws ServiceException {
        //given
        //when
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute(SESSION_ROLE)).thenReturn(Role.USER);
        when(sessionMock.getAttribute(ID)).thenReturn(ID_LONG);
        when(userServiceMock.getUserById(ID_LONG)).thenReturn(Optional.of(USER_VALID));
        when(tariffPlanServiceMock.getTariffPlanById(ID_LONG)).thenReturn(Optional.of(FIRST_TARIFF));
        when(promotionServiceMock.getPromotionById((ID_LONG))).thenReturn(Optional.of(PROMOTION));
        //then
        CommandResult expected = infoPageCommand.execute(requestMock, responseMock);
        CommandResult actual = CommandResult.forward(INFO_LOCATION);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ServiceException.class)
    public void testShouldReturnExceptionIfUserHaveIncorrectTariff() throws ServiceException {
        //given
        //when
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute(SESSION_ROLE)).thenReturn(Role.USER);
        when(sessionMock.getAttribute(ID)).thenReturn(ID_LONG);
        when(userServiceMock.getUserById(ID_LONG)).thenReturn(Optional.of(USER_INVALID_TARIFF));
        when(tariffPlanServiceMock.getTariffPlanById(ID_LONG)).thenThrow(ServiceException.class);
        //then
        CommandResult expected = infoPageCommand.execute(requestMock, responseMock);
    }

    @Test(expected = ServiceException.class)
    public void testShouldReturnExceptionIfUserHaveIncorrectPromotion() throws ServiceException {
        //given
        //when
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute(SESSION_ROLE)).thenReturn(Role.USER);
        when(sessionMock.getAttribute(ID)).thenReturn(ID_LONG);
        when(userServiceMock.getUserById(ID_LONG)).thenReturn(Optional.of(USER_INVALID_PROMOTION));
        when(tariffPlanServiceMock.getTariffPlanById(ID_LONG)).thenReturn(Optional.of(FIRST_TARIFF));
        when(promotionServiceMock.getPromotionById((ID_LONG))).thenThrow(ServiceException.class);
        //then
        CommandResult expected = infoPageCommand.execute(requestMock, responseMock);
    }
}
