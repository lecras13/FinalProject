package com.epam.web.dao;

import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.DaoException;

import java.util.List;

/**
 * The {@code TariffPlansDao} presents implementation {@link TariffPlan} data access object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface TariffPlansDao extends DaoPersistent<TariffPlan> {

    /**
     * Get the tariff plans list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of tariff plans for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws DaoException;

    /**
     * Get the tariff plans only active list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of tariff plans for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<TariffPlan> getTariffPlansOnlyActiveForPage(int firstRow, int rowCount) throws DaoException;

    /**
     * Get tariff plans only active list
     *
     * @return found {@link List} of tariff plans active only
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<TariffPlan> getTariffPlansOnlyActive() throws DaoException;
}
