package com.epam.web.extractor.impl;

import com.epam.web.entity.User;
import com.epam.web.extractor.EntityExtractor;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserExtractor implements EntityExtractor<User> {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String ROLE = "role";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String STATUS_BLOCK = "status_block";
    private static final String TRAFFIC = "traffic";
    private static final String DISCOUNT = "discount";
    private static final String TARIFF_ID = "tariff_id";
    private static final String PROMOTION_ID = "promotion_id";

    @Override
    public Map<String, Object> parse(User user) {
        Map<String, Object> result = new LinkedHashMap<>();
        Long id = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String role = user.getRole().toString();
        Double totalAmount = user.getTotalAmount();
        Boolean statusBlock = user.getStatusBlock();
        Double traffic = user.getTraffic();
        Integer discount = user.getDiscount();
        Long tariffId = user.getTariffId();
        Long promotionId = user.getPromotionId();

        result.put(LOGIN, login);
        result.put(PASSWORD, password);
        result.put(FIRST_NAME, firstName);
        result.put(LAST_NAME, lastName);
        result.put(ROLE, role);
        result.put(TOTAL_AMOUNT, totalAmount);
        result.put(STATUS_BLOCK, statusBlock);
        result.put(TRAFFIC, traffic);
        result.put(DISCOUNT, discount);
        result.put(TARIFF_ID, tariffId);
        result.put(PROMOTION_ID, promotionId);
        result.put(ID, id);
        return result;
    }
}
