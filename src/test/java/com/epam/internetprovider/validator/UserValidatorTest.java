package com.epam.internetprovider.validator;

import com.epam.internetprovider.entity.Role;
import com.epam.internetprovider.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {
    private static final User VALID_USER = new User(1L, "admin", "admin", "Роман", "Александров", Role.ADMIN, 0.0D, false, 0.0D, null, null, 0);
    private static final User INVALID_FIRST_NAME_USER = new User(1L, "admin", "admin", "", "Александров", Role.ADMIN, 0.0D, false, 0.0D, null, null, 0);
    private static final User INVALID_LAST_NAME_USER = new User(1L, "admin", "admin", "Роман", "", Role.ADMIN, 0.0D, false, 0.0D, null, null, 0);
    private static final User INVALID_PASSWORD_USER = new User(1L, "admin", "", "Роман", "Александров", Role.ADMIN, 0.0D, false, 0.0D, null, null, 0);
    private static final User INVALID_DISCOUNT_USER = new User(1L, "admin", "admin", "Роман", "Александров", Role.ADMIN, 0.0D, false, 0.0D, null, null, 50);

    private final Validator<User> validator = new UserValidator();

    @Test
    public void testValidUserDataShouldValid(){
        //given
        //when
        boolean expected = validator.isValid(VALID_USER);
        //then
        Assert.assertTrue(expected);
    }

    @Test
    public void testUserDataShouldInvalidByFirstName(){
        //given
        //when
        boolean expected = validator.isValid(INVALID_FIRST_NAME_USER);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testUserDataShouldInvalidByLastName(){
        //given
        //when
        boolean expected = validator.isValid(INVALID_LAST_NAME_USER);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testUserDataShouldInvalidByPassword(){
        //given
        //when
        boolean expected = validator.isValid(INVALID_PASSWORD_USER);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testUserDataShouldInvalidByDiscount(){
        //given
        //when
        boolean expected = validator.isValid(INVALID_DISCOUNT_USER);
        //then
        Assert.assertFalse(expected);
    }
}
