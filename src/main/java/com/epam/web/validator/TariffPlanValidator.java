package com.epam.web.validator;

import com.epam.web.entity.TariffPlan;

public class TariffPlanValidator implements Validator<TariffPlan> {
    private static final int MAX_LENGTH = 20;
    private static final int MAX_LENGTH_DESCRIPTION = 255;
    private static final int MAX_AMOUNT = 50;

    @Override
    public boolean isValid(TariffPlan tariffPlan) {
        if ((tariffPlan.getTariffName() == null) || (tariffPlan.getTariffName().isEmpty()) || (tariffPlan.getTariffName().length() > MAX_LENGTH)) {
            return false;
        }

        if ((tariffPlan.getPrice() == null) || (tariffPlan.getPrice() < 0 || (tariffPlan.getPrice() > MAX_AMOUNT))) {
            return false;
        }

        return ((tariffPlan.getDescription() != null) || (!tariffPlan.getDescription().isEmpty()) || (tariffPlan.getDescription().length() < MAX_LENGTH_DESCRIPTION));
    }
}
