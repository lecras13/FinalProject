package com.epam.web.service;

import com.epam.web.entity.Promotion;

import com.epam.web.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PromotionService {

    List<Promotion> getPromotions() throws ServiceException;

    void savePromotion(Long id, String promotionName, Date startDate, Date endDate, String description, Long tariffPlanId, Integer newPrice) throws ServiceException;

    void deletePromotion(Long id) throws ServiceException;

    Optional<Promotion> getPromotionById(Long id) throws ServiceException;
}
