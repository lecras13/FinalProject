package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;
import com.epam.web.service.impl.TariffPlanServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TariffPlanDeleteCommandTest {
    private static final String TARIFF_PLANS_PAGE = "/controller?command=tariffs";
    private static final TariffPlan FIRST_TARIFF = new TariffPlan(1L, "firstTariffName", 1, "firstDescription");
    private static final String ID = "tariff_id";

    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private TariffPlanService tariffPlanServiceMock;
    private TariffPlanDeleteCommand tariffPlanDeleteCommand;

    @Before
    public void setUp() {
        requestMock = Mockito.mock(HttpServletRequest.class);
        responseMock = Mockito.mock(HttpServletResponse.class);
        tariffPlanServiceMock = Mockito.mock(TariffPlanServiceImpl.class);
        tariffPlanDeleteCommand = new TariffPlanDeleteCommand(tariffPlanServiceMock);
    }

    @Test
    public void testTariffPlanDeleteShouldRedirectToTariffPlansPage() throws ServiceException {
        //given
        //when
        when(requestMock.getParameter(ID)).thenReturn(String.valueOf(1L));
        when(tariffPlanServiceMock.getTariffPlanById(anyLong())).thenReturn(Optional.of(FIRST_TARIFF));
        CommandResult expected = tariffPlanDeleteCommand.execute(requestMock, responseMock);
        //then
        CommandResult actual = CommandResult.redirect(TARIFF_PLANS_PAGE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTariffPlansShouldDeleteTariff() throws ServiceException {
        //given
        //when
        when(requestMock.getParameter(ID)).thenReturn(String.valueOf(1L));
        when(tariffPlanServiceMock.getTariffPlanById(anyLong())).thenReturn(Optional.of(FIRST_TARIFF));
        tariffPlanDeleteCommand.execute(requestMock, responseMock);
        //then
        verify(tariffPlanServiceMock, times(1)).deleteTariffPlan(1L);
    }
}
