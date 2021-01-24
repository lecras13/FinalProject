package com.epam.web.validator;

import com.epam.web.entity.Promotion;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class PromotionValidatorTest {
    private static final Promotion VALID_PROMOTION = new Promotion(1L, "action", Date.valueOf("2021-01-25"),
            Date.valueOf("2021-01-30"), "description", 1L, 5, false);
    private static final Promotion INVALID_PROMOTION_NAME = new Promotion(1L, "", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "description", 1L, 5, false);
    private static final Promotion INVALID_PROMOTION_PRICE = new Promotion(1L, "action", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "description", 1L, 55, false);
    private static final Promotion INVALID_PROMOTION_DESCRIPTION = new Promotion(1L, "action", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "", 1L, 5, false);
    private static final Promotion INVALID_PROMOTION_START_DATE = new Promotion(1L, "action", null,
            Date.valueOf("2021-01-30"), "description", 1L, 5, false);
    private static final Promotion INVALID_PROMOTION_END_DATE = new Promotion(1L, "action", Date.valueOf("2021-01-25"),
            Date.valueOf("2021-01-12"), "description", 1L, 5, false);



    private final Validator<Promotion> promotionValidator = new PromotionValidator();

    @Test
    public void testPromotionDataShouldValid() {
        //given
        //when
        boolean expected = promotionValidator.isValid(VALID_PROMOTION);
        //then
        Assert.assertTrue(expected);
    }

    @Test
    public void testPromotionDataShouldInvalidPromotionName() {
        //given
        //when
        boolean expected = promotionValidator.isValid(INVALID_PROMOTION_NAME);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testPromotionDataShouldInvalidByPrice() {
        //given
        //when
        boolean expected = promotionValidator.isValid(INVALID_PROMOTION_PRICE);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testPromotionDataShouldInvalidByDescription() {
        //given
        //when
        boolean expected = promotionValidator.isValid(INVALID_PROMOTION_DESCRIPTION);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testPromotionDataShouldInvalidByStartDate() {
        //given
        //when
        boolean expected = promotionValidator.isValid(INVALID_PROMOTION_START_DATE);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testPromotionDataShouldInvalidByEndDate() {
        //given
        //when
        boolean expected = promotionValidator.isValid(INVALID_PROMOTION_END_DATE);
        //then
        Assert.assertFalse(expected);
    }
}
