package com.epam.internetprovider.service;

import com.epam.internetprovider.entity.TariffPlan;
import com.epam.internetprovider.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code TariffPlanService} presents operations with {@link TariffPlan}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface TariffPlanService {

    /**
     * Getting {@link List} of tariffs plan
     *
     * @return found {@link List} of tariffs plan
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<TariffPlan> getTariffPlans() throws ServiceException;

    /**
     * Get the tariff plan by id
     *
     * @param id the current id
     * @return found {@link Optional} tariff plan
     */
    Optional<TariffPlan> getTariffPlanById(Long id) throws ServiceException;

    /**
     * Save or update the tariff plan
     *
     * @param id the current tariff plan id
     * @param tariffName new name tariff plan
     * @param price new price
     * @param description new description
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    void saveTariffPlan(Long id, String tariffName, Integer price, String description, Boolean status) throws ServiceException;

    /**
     * Get the tariff plans list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of tariff plans for page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws ServiceException;

    /**
     * Get the tariff plans list only active for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of tariff plans for page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<TariffPlan> getTariffPlansOnlyActiveForPage(int firstRow, int rowCount) throws ServiceException;

    /**
     * Get the tariff plans list only active
     *
     * @return found {@link List} of tariff plans only active
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<TariffPlan> getTariffPlansOnlyActive() throws ServiceException;
}
