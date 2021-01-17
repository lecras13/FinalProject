package com.epam.web.extractor.impl;

import com.epam.web.entity.Promotion;
import com.epam.web.extractor.EntityExtractor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class PromotionExtractor implements EntityExtractor<Promotion> {
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String DESCRIPTION = "prescription";
    private static final String TARIFF_ID = "tariff_id";
    private static final String NEW_PRICE = "new_price";

    @Override
    public Map<String, Object> parse(Promotion promotion) {
        Map<String, Object> result = new LinkedHashMap<>();
        Long id = promotion.getId();
        String promotionName = promotion.getPromotionName();
        Date startDate = promotion.getStartDate();
        Date endDate = promotion.getEndDate();
        String description = promotion.getDescription();
        Long tariffPlanId = promotion.getTariffPlanId();
        Integer newPrice = promotion.getNewPrice();

        result.put(PROMOTION_NAME, promotionName);
        result.put(START_DATE, startDate);
        result.put(END_DATE, endDate);
        result.put(DESCRIPTION, description);
        result.put(TARIFF_ID, tariffPlanId);
        result.put(NEW_PRICE, newPrice);
        result.put(ID, id);
        return result;
    }
}
