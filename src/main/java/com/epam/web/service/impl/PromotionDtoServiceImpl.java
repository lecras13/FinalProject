package com.epam.web.service.impl;

import com.epam.web.dao.PromotionDtoDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The {@code PromotionDtoServiceImpl} presents implementation {@link PromotionDtoService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDtoServiceImpl implements PromotionDtoService {
    private static final Logger LOGGER = LogManager.getLogger(PromotionDtoServiceImpl.class);
    private final DaoHelperFactory daoHelperFactory;

    public PromotionDtoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<PromotionDto> getPromotionsDtoForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getAllPromotionDtoForPage(firstRow, rowCount);
        } catch (DaoException e) {
            LOGGER.error("Exception promotionDtoService get promotions!");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActiveForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getPromotionsDtoOnlyActiveForPage(firstRow, rowCount);
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService get tariffPlans for a page!");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActive() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getPromotionsDtoOnlyActive();
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService get tariffPlans!");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PromotionDto> getPromotionsDto() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getPromotionsDto();
        } catch (DaoException e) {
            LOGGER.error("Exception tariffPlanService get tariffPlans for a page!");
            throw new ServiceException(e);
        }
    }
}
