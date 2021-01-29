package com.epam.internetprovider.validator;

import com.epam.internetprovider.entity.Payment;

/**
 * The {@code PaymentValidator} represents {@link Payment} validation.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PaymentValidator implements Validator<Payment> {
    private static final int MAX_AMOUNT = 500;

    /**
     * Check {@link Payment} for valid.
     *
     * @param payment the current object
     * @return the boolean
     */
    @Override
    public boolean isValid(Payment payment) {
        return (payment.getAmount() != null) && (payment.getAmount() >= 0 && (payment.getAmount() <= MAX_AMOUNT));
    }
}
