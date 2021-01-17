package com.epam.web.entity;

public enum Role {
    ADMIN("ADMIN"), USER("USER"), GUEST("GUEST");

    String currency;

    Role(String currency) {
        this.currency = currency;
    }
}
