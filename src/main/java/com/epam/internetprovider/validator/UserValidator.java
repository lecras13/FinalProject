package com.epam.internetprovider.validator;

import com.epam.internetprovider.entity.User;

/**
 * The {@code UserValidator} represents {@link User} validation.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */
public class UserValidator implements Validator<User> {
    private static final int MAX_LENGTH = 25;
    private static final int MAX_LENGTH_PASSWORD = 50;

    /**
     * Check {@link User} for valid.
     *
     * @param user the current object
     * @return the boolean
     */
    @Override
    public boolean isValid(User user) {
        if ((user.getPassword() == null) || (user.getPassword().isEmpty() || (user.getPassword().length() > MAX_LENGTH_PASSWORD))) {
            return false;
        }

        if ((user.getLogin() == null) || (user.getLogin().isEmpty()) || (user.getLogin().length() > MAX_LENGTH)) {
            return false;
        }

        if ((user.getFirstName() == null) || (user.getFirstName().isEmpty()) || (user.getFirstName().length() > MAX_LENGTH)) {
            return false;
        }
        if ((user.getLastName() == null) || (user.getLastName().isEmpty()) || (user.getLastName().length() > MAX_LENGTH)) {
            return false;
        }
        return (user.getDiscount() != null) && (user.getDiscount() <= 25) && (user.getDiscount() >= 0);
    }
}
