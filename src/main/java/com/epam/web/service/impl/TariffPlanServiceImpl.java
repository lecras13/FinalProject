package com.epam.web.service.impl;

import com.epam.web.dao.TariffPlansDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionService;
import com.epam.web.service.TariffPlanService;
import com.epam.web.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The {@code TariffPlanServiceImpl} presents implementation {@link TariffPlanService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffPlanServiceImpl implements TariffPlanService {
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
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveTariffPlan(Long id, String tariffName, Integer price , String description, Boolean status) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            TariffPlan tariffPlan = new TariffPlan(id, tariffName, price, description, status);
            if (!tariffPlanValidator.isValid(tariffPlan)) {
                throw new ServiceException("Tariff plan not valid!");
            }
            dao.save(tariffPlan);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<TariffPlan> getTariffPlanById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            return dao.getTariffPlansForPage(firstRow, rowCount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TariffPlan> getTariffPlansOnlyActiveForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            return dao.getTariffPlansOnlyActiveForPage(firstRow, rowCount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TariffPlan> getTariffPlansOnlyActive() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            TariffPlansDao dao = factory.createTariffDao();
            return dao.getTariffPlansOnlyActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
