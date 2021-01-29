package com.epam.internetprovider.entity;

/**
 * The {@code Role} enum presents role for management
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public enum Role {
    ADMIN("ADMIN"), USER("USER");

    String currency;

    Role(String currency) {
        this.currency = currency;
    }
}
