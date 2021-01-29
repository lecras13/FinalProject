package com.epam.internetprovider.validator;

import com.epam.internetprovider.entity.Promotion;

/**
 * The {@code PromotionValidator} represents {@link Promotion} validation.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */
public class PromotionValidator implements Validator<Promotion> {
    private static final int MAX_LENGTH = 50;
    private static final int MAX_LENGTH_DESCRIPTION = 255;
    private static final int MAX_AMOUNT = 50;

    /**
     * Check {@link Promotion} for valid.
     *
     * @param promotion the current object
     * @return the boolean
     */
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

        return (promotion.getStartDate() != null) && (promotion.getEndDate() != null) && (!promotion.getEndDate().before(promotion.getStartDate()));
    }

}
