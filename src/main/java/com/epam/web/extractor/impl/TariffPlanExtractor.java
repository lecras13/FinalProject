package com.epam.web.extractor.impl;

import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.User;
import com.epam.web.extractor.EntityExtractor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@code TariffPlanExtractor} class represents the implementation of writing {@link TariffPlan} fields to the {@link Map}.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffPlanExtractor implements EntityExtractor<TariffPlan> {
    private static final String ID = "id";
    private static final String TARIFF_NAME = "tariff-name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";

    /**
     * Writing object {@link TariffPlan} fields to the map
     *
     * @param tariffPlan the current object {@link TariffPlan}
     */
    @Override
    public Map<String, Object> parse(TariffPlan tariffPlan) {
        Map<String, Object> result = new LinkedHashMap<>();
        Long id = tariffPlan.getId();
        String tariffName = tariffPlan.getTariffName();
        Integer price = tariffPlan.getPrice();
        String description = tariffPlan.getDescription();
        Boolean status = tariffPlan.getBlock();

        result.put(TARIFF_NAME, tariffName);
        result.put(PRICE, price);
        result.put(DESCRIPTION, description);
        result.put(STATUS, status);
        result.put(ID, id);
        return result;
    }
}
