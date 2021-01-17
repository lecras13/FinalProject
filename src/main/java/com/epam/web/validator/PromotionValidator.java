package com.epam.web.validator;

import com.epam.web.entity.Promotion;

import java.util.Date;

public class PromotionValidator implements Validator<Promotion> {
    private static final int MAX_LENGTH = 50;
    private static final int MAX_LENGTH_DESCRIPTION = 255;
    private static final int MAX_AMOUNT = 50;
    private final Date date = new Date();

    @Override
    public boolean isValid(Promotion promotion) {
        if ((promotion.getPromotionName() == null) || (promotion.getPromotionName().isEmpty()) || (promotion.getPromotionName().length() > MAX_LENGTH)) {
            return false;
        }

        if ((promotion.getNewPrice() == null) || (promotion.getNewPrice() < 0 || (promotion.getNewPrice() > MAX_AMOUNT))) {
            return false;
        }

        if ((promotion.getDescription() == null) || (promotion.getDescription().isEmpty() || (promotion.getDescription().length() > MAX_LENGTH_DESCRIPTION))) {
            return false;
        }

        if ((promotion.getStartDate() == null) || (promotion.getStartDate().after(date))) {
            return false;
        }
        return true;
    }

}
