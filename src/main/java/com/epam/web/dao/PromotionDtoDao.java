package com.epam.web.dao;

import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.DaoException;

import java.util.List;

/**
 * The {@code PromotionDaoDto} presents implementation {@link PromotionDto} data access object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface PromotionDtoDao extends Dao<PromotionDto> {

    /**
     * Get the promotions list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of promotions for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<PromotionDto> getAllPromotionDtoForPage(int firstRow, int rowCount) throws DaoException;

    /**
     * Get the promotions only active list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of promotions for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<PromotionDto> getPromotionsDtoOnlyActiveForPage(int firstRow, int rowCount) throws DaoException;
}
