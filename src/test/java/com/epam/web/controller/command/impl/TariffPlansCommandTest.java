package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.command.PageController;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class TariffPlansCommandTest {
    private static final String TARIFF_PLANS_PAGE = "/WEB-INF/view/pages/tariff-plans.jsp";
    private static final String TARIFF_PLANS = "tariffs";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final TariffPlan FIRST_TARIFF = new TariffPlan(1L, "firstTariffName", 1, "firstDescription");
    private static final TariffPlan SECOND_TARIFF = new TariffPlan(2L, "secondTariffName", 2, "secondDescription");
    private static final TariffPlan THIRD_TARIFF = new TariffPlan(3L, "thirdTariffName", 3, "thirdDescription");
    private static final List<TariffPlan> TARIFF_PLANS_LIST = Arrays.asList(FIRST_TARIFF, SECOND_TARIFF, THIRD_TARIFF);


    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private TariffPlanService tariffPlanServiceMock;
    private PageController pageControllerMock;
    private TariffPlansCommand tariffPlansCommand;

    @Before
    public void setUp() {
        requestMock = Mockito.mock(HttpServletRequest.class);
        responseMock = Mockito.mock(HttpServletResponse.class);
        tariffPlanServiceMock = Mockito.mock(TariffPlanServiceImpl.class);
        pageControllerMock = Mockito.mock(PageController.class);
        tariffPlansCommand = new TariffPlansCommand(tariffPlanServiceMock, pageControllerMock);
    }

    @Test
    public void testTariffPlansShouldCheckValidCurrentPage() throws ServiceException {
        //given
        //when
        setValidAttributes();
        tariffPlansCommand.execute(requestMock, responseMock);
        //then
        verify(requestMock, times(1)).setAttribute(CURRENT_PAGE, 1);
    }

    @Test
    public void testTariffPlansShouldCheckValidNoOfPage() throws ServiceException {
        //given
        //when
        setValidAttributes();
        tariffPlansCommand.execute(requestMock, responseMock);
        //then
        verify(requestMock, times(1)).setAttribute(NO_OF_PAGES, 1);
    }

    @Test
    public void testTariffPlansShouldCheckValidTariffPlans() throws ServiceException {
        //given
        //when
        setValidAttributes();
        tariffPlansCommand.execute(requestMock, responseMock);
        //then
        verify(requestMock, times(1)).setAttribute(TARIFF_PLANS, TARIFF_PLANS_LIST);
    }

    @Test
    public void testTariffPlansShouldReturn() throws ServiceException {
        //given
        //when
        CommandResult expected = tariffPlansCommand.execute(requestMock, responseMock);
        //then
        CommandResult actual = CommandResult.forward(TARIFF_PLANS_PAGE);
        Assert.assertEquals(expected, actual);
    }

    private void setValidAttributes() throws ServiceException {
        when(pageControllerMock.getCurrentPage(requestMock)).thenReturn(1);
        when(tariffPlanServiceMock.getTariffPlansForPage(anyInt(), anyInt())).thenReturn(TARIFF_PLANS_LIST);
        when(tariffPlanServiceMock.getTariffPlans()).thenReturn(TARIFF_PLANS_LIST);
        when(pageControllerMock.getNumberPages(anyInt(), anyInt())).thenReturn(1);
    }
}
