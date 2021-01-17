package com.epam.web.validator;

import com.epam.web.entity.Payment;

public class PaymentValidator implements Validator<Payment> {
    private static final int MAX_AMOUNT = 500;

    @Override
    public boolean isValid(Payment payment) {
        if ((payment.getAmount() == null) || (payment.getAmount() < 0 || (payment.getAmount() > MAX_AMOUNT))) {
            return false;
        }
        return true;
    }
}
