package com.epam.web.entity;

/**
 * The {@code Role} enum presents role for management
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public enum Role {
    ADMIN("ADMIN"), USER("USER"), GUEST("GUEST");

    String currency;

    Role(String currency) {
        this.currency = currency;
    }
}
