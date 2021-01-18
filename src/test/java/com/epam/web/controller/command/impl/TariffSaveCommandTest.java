package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.CommandResult;
import com.epam.web.service.TariffPlanService;
import com.epam.web.service.impl.TariffPlanServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TariffSaveCommandTest {
    private static final String TARIFF_PLANS_PAGE = "/controller?command=tariffs";
    private static final String ID = "id";
    private static final String TARIFF_NAME = "tariff";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";

    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private TariffPlanService tariffPlanServiceMock;
    private TariffPlanSaveCommand tariffPlanSaveCommand;

    @Before
    public void setUp(){
        requestMock = Mockito.mock(HttpServletRequest.class);
        responseMock = Mockito.mock(HttpServletResponse.class);
        tariffPlanServiceMock = Mockito.mock(TariffPlanServiceImpl.class);
        tariffPlanSaveCommand = new TariffPlanSaveCommand(tariffPlanServiceMock);
    }

    @Test
    public void testTariffPlanSaveShouldRedirectToTariffPlansPage(){
        //given
        //when
        when(requestMock.getParameter(ID)).thenReturn(String.valueOf(anyLong()));
        when(requestMock.getParameter(TARIFF_NAME)).thenReturn(anyString());
        when(requestMock.getParameter(PRICE)).thenReturn(String.valueOf(anyInt()));
        when(requestMock.getParameter(DESCRIPTION)).thenReturn(anyString());
        CommandResult expected = tariffPlanSaveCommand.execute(requestMock, responseMock);
        //then
        CommandResult actual = CommandResult.redirect(TARIFF_PLANS_PAGE);
        Assert.assertEquals(expected, actual);
    }
}
