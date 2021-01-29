package com.epam.internetprovider.validator;

import com.epam.internetprovider.entity.Payment;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class PaymentValidatorTest {
    private static final Payment VALID_PAYMENT = new Payment(1L, 10.0, Date.valueOf("2021-01-25"), 1L);
    private static final Payment INVALID_PAYMENT_AMOUNT_FIRST = new Payment(1L, 501.0, Date.valueOf("2021-01-25"), 1L);
    private static final Payment INVALID_PAYMENT_AMOUNT_SECOND = new Payment(1L, null, Date.valueOf("2021-01-25"), 1L);
    private static final Payment INVALID_PAYMENT_AMOUNT_THIRD = new Payment(1L, -10.0, Date.valueOf("2021-01-25"), 1L);

    private final Validator<Payment> paymentValidator = new PaymentValidator();

    @Test
    public void testPaymentDataShouldValid() {
        //given
        //when
        boolean expected = paymentValidator.isValid(VALID_PAYMENT);
        //then
        Assert.assertTrue(expected);
    }

    @Test
    public void testPaymentDataShouldInvalidByToBigAmount() {
        //given
        //when
        boolean expected = paymentValidator.isValid(INVALID_PAYMENT_AMOUNT_FIRST);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testPaymentDataShouldInvalidByNullAmount() {
        //given
        //when
        boolean expected = paymentValidator.isValid(INVALID_PAYMENT_AMOUNT_SECOND);
        //then
        Assert.assertFalse(expected);
    }

    @Test
    public void testPaymentDataShouldInvalidByNegativeAmount() {
        //given
        //when
        boolean expected = paymentValidator.isValid(INVALID_PAYMENT_AMOUNT_THIRD);
        //then
        Assert.assertFalse(expected);
    }
}
