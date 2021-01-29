package com.epam.internetprovider.service;

import com.epam.internetprovider.entity.Promotion;

import com.epam.internetprovider.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The {@code PromotionService} presents operations {@link Promotion}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface PromotionService {

    /**
     * Getting {@link List} of promotions
     *
     * @return found {@link List} of promotions
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<Promotion> getPromotions() throws ServiceException;

    /**
     * Save or update the promotion
     *
     * @param id            the current promotion id
     * @param promotionName new name promotion
     * @param startDate     new start date for promotion
     * @param endDate       new end date for promotion
     * @param description   new description
     * @param tariffPlanId  id tariff plan for promotion
     * @param newPrice      new price
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    void savePromotion(Long id, String promotionName, Date startDate, Date endDate, String description, Long tariffPlanId, Integer newPrice, Boolean status) throws ServiceException;

    /**
     * Get the promotion by id
     *
     * @param id the current id
     * @return found {@link Optional} promotion
     */
    Optional<Promotion> getPromotionById(Long id) throws ServiceException;
}
