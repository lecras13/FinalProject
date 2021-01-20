package com.epam.web.service;

import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.ServiceException;

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
    List<PromotionDto> getPromotionDtoForPage(int firstRow, int rowCount) throws ServiceException;
}
