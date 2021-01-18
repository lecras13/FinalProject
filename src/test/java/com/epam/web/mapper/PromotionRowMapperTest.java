package com.epam.web.mapper;

import com.epam.web.entity.Promotion;
import com.epam.web.mapper.impl.PromotionRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class PromotionRowMapperTest {
    private static final Promotion PROMOTION = new Promotion(5L, "action", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "description", 5L, 5);
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String DESCRIPTION = "description";
    private static final String TARIFF_ID = "tariff_id";
    private static final String NEW_PRICE = "new_price";

    private PromotionRowMapper promotionRowMapper;
    private ResultSet resultSet;

    @Before
    public void setUp(){
        promotionRowMapper = new PromotionRowMapper();
        resultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    public void testMapShouldReturnAccountWithNoPassword() throws SQLException{
        //given
        //when
        when(resultSet.getLong(ID)).thenReturn(5L);
        when(resultSet.getString(PROMOTION_NAME)).thenReturn("action");
        when(resultSet.getDate(START_DATE)).thenReturn(Date.valueOf("2021-01-15"));
        when(resultSet.getDate(END_DATE)).thenReturn(Date.valueOf("2021-01-15"));
        when(resultSet.getString(DESCRIPTION)).thenReturn(DESCRIPTION);
        when(resultSet.getLong(TARIFF_ID)).thenReturn(5L);
        when(resultSet.getInt(NEW_PRICE)).thenReturn(5);
        //then
        Promotion actual = promotionRowMapper.map(resultSet);
        Assert.assertEquals(PROMOTION, actual);
    }

    @Test(expected = SQLException.class)
    public void testMapShouldReturnSQLException() throws SQLException{
        //given
        //when
        when(resultSet.getLong(ID)).thenThrow(SQLException.class);
        //then
        Promotion actual = promotionRowMapper.map(resultSet);
    }
}
