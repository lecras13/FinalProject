package com.epam.web.extractor.impl;

import com.epam.web.entity.Payment;
import com.epam.web.entity.Promotion;
import com.epam.web.extractor.EntityExtractor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@code PaymentExtractor} class represents the implementation of writing {@link Payment} fields to the {@link Map}.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PaymentExtractor implements EntityExtractor<Payment> {
    private static final String ID = "id";
    private static final String AMOUNT = "amount";
    private static final String PAYMENT_DATE = "payment_date";
    private static final String USER_ID = "user_id";

    /**
     * Writing object {@link Payment} fields to the map
     *
     * @param payment the current object {@link Payment}
     */
    @Override
    public Map<String, Object> parse(Payment payment) {
        Map<String, Object> result = new LinkedHashMap<>();
        Long id = payment.getId();
        Double amount = payment.getAmount();
        Date paymentDate = payment.getPaymentDate();
        Long userId = payment.getUserId();

        result.put(AMOUNT, amount);
        result.put(PAYMENT_DATE, paymentDate);
        result.put(USER_ID, userId);
        result.put(ID, id);
        return result;
    }
}
