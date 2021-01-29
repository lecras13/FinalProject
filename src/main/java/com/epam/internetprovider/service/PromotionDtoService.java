package com.epam.internetprovider.service;

import com.epam.internetprovider.entity.dto.PromotionDto;
import com.epam.internetprovider.exception.ServiceException;

import java.util.List;

/**
 * The {@code PromotionDtoService} presents operations {@link PromotionDto}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface PromotionDtoService {

    /**
     * Get the promotions dto list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of promotions dto for page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<PromotionDto> getPromotionsDtoForPage(int firstRow, int rowCount) throws ServiceException;

    /**
     * Get the promotions list only active for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of promotions for page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<PromotionDto> getPromotionsDtoOnlyActiveForPage(int firstRow, int rowCount) throws ServiceException;

    /**
     * Getting {@link List} promotions only active
     *
     * @return found {@link List} promotions only active
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<PromotionDto> getPromotionsDtoOnlyActive() throws ServiceException;

    /**
     * Getting {@link List} promotions
     *
     * @return found {@link List} promotions
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<PromotionDto> getPromotionsDto() throws ServiceException;
}
