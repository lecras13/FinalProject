package com.epam.web.service.impl;

import com.epam.web.dao.PromotionDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.Promotion;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionService;
import com.epam.web.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PromotionServiceImpl implements PromotionService {
    private static final Logger LOGGER = LogManager.getLogger(PromotionServiceImpl.class);
    private final DaoHelperFactory daoHelperFactory;
    private final Validator<Promotion> promotionValidator;

    public PromotionServiceImpl(DaoHelperFactory daoHelperFactory, Validator<Promotion> promotionValidator) {
        this.daoHelperFactory = daoHelperFactory;
        this.promotionValidator = promotionValidator;
    }


    @Override
    public List<Promotion> getPromotions() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDao dao = factory.createPromotionDao();
            return dao.getAll();
        } catch (DaoException  e) {
            LOGGER.error("Exception promotionService get promotions!");
            throw new ServiceException(e);
        }
    }

    @Override
    public void savePromotion(Long id, String promotionName, Date startDate, Date endDate, String description, Long tariffPlanId, Integer newPrice) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDao dao = factory.createPromotionDao();
            Promotion promotion = new Promotion(id, promotionName, startDate, endDate, description, tariffPlanId, newPrice);
            if (!promotionValidator.isValid(promotion)) {
                LOGGER.error("Promotion not valid!");
                throw new ServiceException();
            }
            dao.save(promotion);
        } catch (DaoException e) {
            LOGGER.error("Exception promotionService saving promotion!");
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePromotion(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDao dao = factory.createPromotionDao();
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error("Exception promotionService deleting promotion!");
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Promotion> getPromotionById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDao dao = factory.createPromotionDao();
            return dao.getById(id);
        } catch (DaoException e) {
            LOGGER.error("Exception promotionService get promotion by id!");
            throw new ServiceException(e);
        }
    }
}
