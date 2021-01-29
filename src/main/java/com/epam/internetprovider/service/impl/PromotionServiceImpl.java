package com.epam.internetprovider.service.impl;

import com.epam.internetprovider.dao.PromotionDao;
import com.epam.internetprovider.dao.helper.DaoHelper;
import com.epam.internetprovider.dao.helper.DaoHelperFactory;
import com.epam.internetprovider.entity.Promotion;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.PromotionService;
import com.epam.internetprovider.validator.Validator;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The {@code PromotionServiceImpl} presents implementation {@link PromotionService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionServiceImpl implements PromotionService {
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
            throw new ServiceException(e);
        }
    }

    @Override
    public void savePromotion(Long id, String promotionName, Date startDate, Date endDate, String description, Long tariffPlanId, Integer newPrice, Boolean status) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDao dao = factory.createPromotionDao();
            Promotion promotion = new Promotion(id, promotionName, startDate, endDate, description, tariffPlanId, newPrice, status);
            if (!promotionValidator.isValid(promotion)) {
                throw new ServiceException("Promotion not valid!");
            }
            dao.save(promotion);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Promotion> getPromotionById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDao dao = factory.createPromotionDao();
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
