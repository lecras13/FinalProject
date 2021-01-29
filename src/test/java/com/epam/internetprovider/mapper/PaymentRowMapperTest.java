package com.epam.internetprovider.mapper;

import com.epam.internetprovider.entity.Payment;
import com.epam.internetprovider.mapper.impl.PaymentRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class PaymentRowMapperTest {
    private static final Payment EXPECTED_PAYMENT = new Payment(5L, 5D, Date.valueOf("2021-01-15"), 1L);
    private static final String ID = "id";
    private static final String AMOUNT = "amount";
    private static final String PAYMENT_DATE = "payment_date";
    private static final String USER_ID = "user_id";

    private PaymentRowMapper paymentRowMapper;
    private ResultSet resultSet;

    @Before
    public void setUp(){
        paymentRowMapper = new PaymentRowMapper();
        resultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    public void testMapShouldReturnAccountWithNoPassword() throws SQLException{
        //given
        //when
        when(resultSet.getLong(ID)).thenReturn(5L);
        when(resultSet.getDouble(AMOUNT)).thenReturn(5D);
        when(resultSet.getDate(PAYMENT_DATE)).thenReturn(Date.valueOf("2021-01-15"));
        when(resultSet.getLong(USER_ID)).thenReturn(1L);
        //then
        Payment actual = paymentRowMapper.map(resultSet);
        Assert.assertEquals(EXPECTED_PAYMENT, actual);
    }

    @Test(expected = SQLException.class)
    public void testMapShouldReturnSQLException() throws SQLException{
        //given
        //when
        when(resultSet.getLong(ID)).thenThrow(SQLException.class);
        //then
        Payment actual = paymentRowMapper.map(resultSet);
    }
}
