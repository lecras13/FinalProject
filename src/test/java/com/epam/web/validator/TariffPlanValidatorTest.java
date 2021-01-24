package com.epam.web.validator;

import com.epam.web.entity.TariffPlan;
import org.junit.Assert;
import org.junit.Test;

public class TariffPlanValidatorTest {
    private static final TariffPlan VALID_TARIFF_PLAN = new TariffPlan(1L, "SUPER", 15, "description", false);
    private static final TariffPlan INVALID_TARIFF_NAME_PLAN = new TariffPlan(1L, "SUPER-SUPER-SUPER-SUPER", 15, "description", false);
    private static final TariffPlan INVALID_TARIFF_PRICE_PLAN = new TariffPlan(1L, "SUPER", 51, "description", false);
    private static final TariffPlan INVALID_TARIFF_DESCRIPTION_PLAN = new TariffPlan(1L, "SUPER", 5, "", false);

    private final Validator<TariffPlan> tariffPlanValidator = new TariffPlanValidator();

    @Test
    public void testTariffPlanDataShouldValid() {
        //given
        //when
        boolean expected = tariffPlanValidator.isValid(VALID_TARIFF_PLAN);
        //then
        Assert.assertTrue(expected);
    }

    @Test
    public void testTariffPlanDataShouldInvalidTariffName(){
        //given
        //when
        boolean expected = tariffPlanValidator.isValid(INVALID_TARIFF_NAME_PLAN);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testTariffPlanDataShouldInvalidByPrice(){
        //given
        //when
        boolean expected = tariffPlanValidator.isValid(INVALID_TARIFF_PRICE_PLAN);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testTariffPlanDataShouldInvalidByDescription(){
        //given
        //when
        boolean expected = tariffPlanValidator.isValid(INVALID_TARIFF_DESCRIPTION_PLAN);
        //then
        Assert.assertFalse(expected);
    }
}
