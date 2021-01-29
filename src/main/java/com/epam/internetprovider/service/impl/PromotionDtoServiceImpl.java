package com.epam.internetprovider.service.impl;

import com.epam.internetprovider.dao.PromotionDtoDao;
import com.epam.internetprovider.dao.helper.DaoHelper;
import com.epam.internetprovider.dao.helper.DaoHelperFactory;
import com.epam.internetprovider.entity.dto.PromotionDto;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.PromotionDtoService;

import java.util.List;

/**
 * The {@code PromotionDtoServiceImpl} presents implementation {@link PromotionDtoService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDtoServiceImpl implements PromotionDtoService {
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
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActiveForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getPromotionsDtoOnlyActiveForPage(firstRow, rowCount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActive() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getPromotionsDtoOnlyActive();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PromotionDto> getPromotionsDto() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            PromotionDtoDao dao = factory.createPromotionDtoDao();
            return dao.getPromotionsDto();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
