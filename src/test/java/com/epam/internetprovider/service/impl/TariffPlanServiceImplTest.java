package com.epam.internetprovider.service.impl;

import com.epam.internetprovider.dao.TariffPlansDao;
import com.epam.internetprovider.dao.helper.DaoHelper;
import com.epam.internetprovider.dao.helper.DaoHelperFactory;
import com.epam.internetprovider.entity.TariffPlan;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.TariffPlanService;
import com.epam.internetprovider.validator.TariffPlanValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TariffPlanServiceImplTest {
    private static final TariffPlan FIRST_TARIFF_PLAN = new TariffPlan(1L, "SUPER", 15, "description", false);
    private static final Optional<TariffPlan> FIRST_TARIFF_PLAN_OPTIONAL = Optional.of(new TariffPlan(1L, "SUPER", 15, "description", false));
    private static final TariffPlan SECOND_TARIFF_PLAN = new TariffPlan(2L, "SUPER", 15, "description", false);
    private static final TariffPlan THIRD_TARIFF_PLAN = new TariffPlan(3L, "SUPER", 15, "description", false);
    private static final TariffPlan FOURTH_TARIFF_PLAN = new TariffPlan(4L, "SUPER", 15, "description", true);
    private static final TariffPlan FIFTH_TARIFF_PLAN = new TariffPlan(5L, "SUPER", 15, "description", true);
    private static final TariffPlan SIXTH_TARIFF_PLAN = new TariffPlan(6L, "SUPER", 15, "description", true);
    private static final int FIRST_ROW = 1;
    private static final int ROW_COUNT = 2;
    private static final Long ID = 1L;
    private static final List<TariffPlan> TARIFF_PLAN_ALL = Arrays.asList(FIRST_TARIFF_PLAN, SECOND_TARIFF_PLAN, THIRD_TARIFF_PLAN, FOURTH_TARIFF_PLAN, FIFTH_TARIFF_PLAN, SIXTH_TARIFF_PLAN);
    private static final List<TariffPlan> TARIFF_PLAN_ALL_FOR_PAGE = Arrays.asList(FIRST_TARIFF_PLAN, SECOND_TARIFF_PLAN, THIRD_TARIFF_PLAN, FOURTH_TARIFF_PLAN, FIFTH_TARIFF_PLAN, SIXTH_TARIFF_PLAN);
    private static final List<TariffPlan> TARIFF_PLAN_ACTIVE = Arrays.asList(FIRST_TARIFF_PLAN, SECOND_TARIFF_PLAN, THIRD_TARIFF_PLAN);
    private static final List<TariffPlan> TARIFF_PLAN_ACTIVE_FOR_PAGE = Arrays.asList(FIRST_TARIFF_PLAN, SECOND_TARIFF_PLAN);


    private DaoHelperFactory helperFactoryMock;
    private DaoHelper helperMock;
    private TariffPlansDao tariffPlansDaoMock;
    private TariffPlanService tariffPlanService;

    @Before
    public void setUp() {
        helperFactoryMock = Mockito.mock(DaoHelperFactory.class);
        helperMock = Mockito.mock(DaoHelper.class);
        tariffPlansDaoMock = Mockito.mock(TariffPlansDao.class);
        TariffPlanValidator tariffPlanValidator = Mockito.mock(TariffPlanValidator.class);
        tariffPlanService = new TariffPlanServiceImpl(helperFactoryMock, tariffPlanValidator);
    }

    @Test
    public void testTariffPlansOnlyActiveReturnAllActivePlans() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getTariffPlansOnlyActive()).thenReturn(TARIFF_PLAN_ACTIVE);

        List<TariffPlan> actual = tariffPlanService.getTariffPlansOnlyActive();

        Assert.assertEquals(actual, TARIFF_PLAN_ACTIVE);
    }

    @Test(expected = ServiceException.class)
    public void testTariffPlansOnlyActiveReturnServiceException() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getTariffPlansOnlyActive()).thenThrow(DaoException.class);

        List<TariffPlan> actual = tariffPlanService.getTariffPlansOnlyActive();
    }

    @Test
    public void testTariffPlansOnlyActiveForPageReturnAllActivePlansForPage() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getTariffPlansOnlyActiveForPage(FIRST_ROW, ROW_COUNT)).thenReturn(TARIFF_PLAN_ACTIVE_FOR_PAGE);

        List<TariffPlan> actual = tariffPlanService.getTariffPlansOnlyActiveForPage(FIRST_ROW, ROW_COUNT);

        Assert.assertEquals(actual, TARIFF_PLAN_ACTIVE_FOR_PAGE);
    }

    @Test(expected = ServiceException.class)
    public void testTariffPlansOnlyActiveForPageReturnServiceException() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getTariffPlansOnlyActiveForPage(FIRST_ROW, ROW_COUNT)).thenThrow(DaoException.class);

        List<TariffPlan> actual = tariffPlanService.getTariffPlansOnlyActiveForPage(FIRST_ROW, ROW_COUNT);
    }

    @Test
    public void testTariffPlansReturnAllPlans() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getAll()).thenReturn(TARIFF_PLAN_ALL);

        List<TariffPlan> actual = tariffPlanService.getTariffPlans();

        Assert.assertEquals(actual, TARIFF_PLAN_ALL);
    }

    @Test(expected = ServiceException.class)
    public void testTariffPlansReturnAllPlansReturnServiceException() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getAll()).thenThrow(DaoException.class);

        List<TariffPlan> actual = tariffPlanService.getTariffPlans();
    }

    @Test
    public void testTariffPlansForPageReturnAllPlansForPage() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getTariffPlansForPage(FIRST_ROW, ROW_COUNT)).thenReturn(TARIFF_PLAN_ALL_FOR_PAGE);

        List<TariffPlan> actual = tariffPlanService.getTariffPlansForPage(FIRST_ROW, ROW_COUNT);

        Assert.assertEquals(actual, TARIFF_PLAN_ALL_FOR_PAGE);
    }

    @Test(expected = ServiceException.class)
    public void testTariffPlansForPageReturnAllPlansForPageReturnServiceException() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getTariffPlansForPage(FIRST_ROW, ROW_COUNT)).thenThrow(DaoException.class);

        List<TariffPlan> actual = tariffPlanService.getTariffPlansForPage(FIRST_ROW, ROW_COUNT);
    }

    @Test
    public void testTariffPlanByIdReturnPlanById() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getById(ID)).thenReturn(FIRST_TARIFF_PLAN_OPTIONAL);

        Optional<TariffPlan> actual = tariffPlanService.getTariffPlanById(ID);

        Assert.assertEquals(actual, FIRST_TARIFF_PLAN_OPTIONAL);
    }

    @Test(expected = ServiceException.class)
    public void testTariffPlanByIdReturnPlanByIdReturnServiceException() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createTariffDao()).thenReturn(tariffPlansDaoMock);
        when(tariffPlansDaoMock.getById(ID)).thenThrow(DaoException.class);

        Optional<TariffPlan> actual = tariffPlanService.getTariffPlanById(ID);
    }
}
