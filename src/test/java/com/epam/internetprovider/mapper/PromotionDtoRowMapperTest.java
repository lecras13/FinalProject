package com.epam.internetprovider.mapper;

import com.epam.internetprovider.entity.dto.PromotionDto;
import com.epam.internetprovider.mapper.impl.PromotionDtoRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class PromotionDtoRowMapperTest {
    private static final PromotionDto PROMOTION_DTO = new PromotionDto(5L, "action", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "description", "tariffName", 1L, 15, false);
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String TARIFF_ID = "tariff_id";
    private static final String TARIFF_NAME = "tariff_name";
    private static final String DESCRIPTION = "description";

    private static final String NEW_PRICE = "new_price";
    private static final String STATUS = "status";

    private PromotionDtoRowMapper promotionDtoRowMapper;
    private ResultSet resultSet;

    @Before
    public void setUp() {
        promotionDtoRowMapper = new PromotionDtoRowMapper();
        resultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    public void testMapShouldReturnAccountWithNoPassword() throws SQLException {
        //given
        //when
        when(resultSet.getLong(ID)).thenReturn(5L);
        when(resultSet.getString(PROMOTION_NAME)).thenReturn("action");
        when(resultSet.getDate(START_DATE)).thenReturn(Date.valueOf("2021-01-15"));
        when(resultSet.getDate(END_DATE)).thenReturn(Date.valueOf("2021-01-15"));
        when(resultSet.getString(DESCRIPTION)).thenReturn("description");
        when(resultSet.getLong(TARIFF_ID)).thenReturn(1L);
        when(resultSet.getString(TARIFF_NAME)).thenReturn("tariffName");
        when(resultSet.getInt(NEW_PRICE)).thenReturn(15);
        when(resultSet.getBoolean(STATUS)).thenReturn(false);
        //then
        PromotionDto actual = promotionDtoRowMapper.map(resultSet);
        Assert.assertEquals(PROMOTION_DTO, actual);
    }

    @Test(expected = SQLException.class)
    public void testMapShouldReturnSQLException() throws SQLException {
        //given
        //when
        when(resultSet.getLong(ID)).thenThrow(SQLException.class);
        //then
        PromotionDto actual = promotionDtoRowMapper.map(resultSet);
    }
}
