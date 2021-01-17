package com.epam.web.service.impl;

import com.epam.web.dao.TariffPlansDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;
import com.epam.web.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TariffPlanServiceImpl implements TariffPlanService {
    private static final Logger LOGGER = LogManager.getLogger(TariffPlanServiceImpl.class);
    private final DaoHelperFactory daoHelperFactory;
    private final Validator<TariffPlan> tariffPlanValidator;

    public TariffPlanServiceImpl(DaoHelperFactory daoHelperFactory, Validator<TariffPlan> tariffPlanValidator) {
        this.daoHelperFactory = daoHelperFactory;
        this.tariffPlanValidator = tariffPlanValidator;
    }

    @Override
    public List<TariffPlan> getTariffPlans() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            return dao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService get tariffPlans!");
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveTariffPlan(Long id, String tariffName, Integer price , String description) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            TariffPlan tariffPlan = new TariffPlan(id, tariffName, price, description);
            if (!tariffPlanValidator.isValid(tariffPlan)) {
                LOGGER.error("TariffPlan not valid!");
                throw new ServiceException();
            }
            dao.save(tariffPlan);
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService saving tariffPlan!");
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteTariffPlan(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService deleting tariffPlan!");
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<TariffPlan> getTariffPlanById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            return dao.getById(id);
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService getting tariffPlan by id!");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            return dao.getTariffPlansForPage(firstRow, rowCount);
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService get tariffPlans for a page!");
            throw new ServiceException(e);
        }
    }
}
