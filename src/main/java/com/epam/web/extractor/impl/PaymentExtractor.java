package com.epam.web.extractor.impl;

import com.epam.web.entity.Payment;
import com.epam.web.extractor.EntityExtractor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class PaymentExtractor implements EntityExtractor<Payment> {
    private static final String ID = "id";
    private static final String AMOUNT = "amount";
    private static final String PAYMENT_DATE = "payment_date";
    private static final String USER_ID = "user_id";

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
