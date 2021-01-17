package com.epam.web.extractor.impl;

import com.epam.web.entity.TariffPlan;
import com.epam.web.extractor.EntityExtractor;

import java.util.LinkedHashMap;
import java.util.Map;

public class TariffPlanExtractor implements EntityExtractor<TariffPlan> {
    private static final String ID = "id";
    private static final String TARIFF_NAME = "tariff-name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";

    @Override
    public Map<String, Object> parse(TariffPlan tariffPlan) {
        Map<String, Object> result = new LinkedHashMap<>();
        Long id = tariffPlan.getId();
        String tariffName = tariffPlan.getTariffName();
        Integer price = tariffPlan.getPrice();
        String description = tariffPlan.getDescription();

        result.put(TARIFF_NAME, tariffName);
        result.put(PRICE, price);
        result.put(DESCRIPTION, description);
        result.put(ID, id);
        return result;
    }
}
