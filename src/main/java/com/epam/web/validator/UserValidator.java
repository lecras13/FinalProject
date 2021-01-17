package com.epam.web.validator;

import com.epam.web.entity.User;

public class UserValidator implements Validator<User> {
    private static final int MAX_LENGTH = 25;
    private static final int MAX_LENGTH_PASSWORD = 50;

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
