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

public class PromotionDtoServiceImpl implements PromotionDtoService {
    private static final Logger LOGGER = LogManager.getLogger(PromotionDtoServiceImpl.class);
    private final DaoHelperFactory daoHelperFactory;

    public PromotionDtoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<PromotionDto> getPromotionDtoForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getAllPromotionDto(firstRow, rowCount);
        } catch (DaoException e) {
            LOGGER.error("Exception promotionDtoService get promotions!");
            throw new ServiceException(e);
        }
    }
}
