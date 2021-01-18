package com.epam.web.mapper;

import com.epam.web.entity.Promotion;
import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.mapper.impl.PromotionDtoRowMapper;
import com.epam.web.mapper.impl.TariffRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.Mockito.when;

public class PromotionDtoRowMapperTest {
    private static final Promotion PROMOTION = new Promotion(5L, "action", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "description", 5L, 5);
    private static final TariffPlan TARIFF_PLAN = new TariffPlan(1L, "tariffName", 1, "description");
    private static final PromotionDto PROMOTION_DTO = new PromotionDto(5L, "action", Date.valueOf("2021-01-15"),
            Date.valueOf("2021-01-15"), "description", TARIFF_PLAN, 5);
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String DESCRIPTION = "description";
    private static final String TARIFF_ID = "tariff_id";
    private static final String NEW_PRICE = "new_price";

    private TariffRowMapper tariffRowMapper;
    private PromotionDtoRowMapper promotionDtoRowMapper;
    private ResultSet resultSet;

    @Before
    public void setUp(){
        tariffRowMapper = Mockito.mock(TariffRowMapper.class);
        promotionDtoRowMapper = new PromotionDtoRowMapper(tariffRowMapper);
        resultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    public void testMapShouldReturnAccountWithNoPassword() throws SQLException{
        //given
        //when
        when(tariffRowMapper.map(resultSet)).thenReturn(TARIFF_PLAN);
        when(resultSet.getLong(ID)).thenReturn(5L);
        when(resultSet.getString(PROMOTION_NAME)).thenReturn("action");
        when(resultSet.getDate(START_DATE)).thenReturn(Date.valueOf("2021-01-15"));
        when(resultSet.getDate(END_DATE)).thenReturn(Date.valueOf("2021-01-15"));
        when(resultSet.getString(DESCRIPTION)).thenReturn(DESCRIPTION);
        when(resultSet.getLong(TARIFF_ID)).thenReturn(5L);
        when(resultSet.getInt(NEW_PRICE)).thenReturn(5);
        //then
        PromotionDto actual = promotionDtoRowMapper.map(resultSet);
        Assert.assertEquals(PROMOTION_DTO, actual);
    }

    @Test(expected = SQLException.class)
    public void testMapShouldReturnSQLException() throws SQLException{
        //given
        //when
        when(resultSet.getLong(ID)).thenThrow(SQLException.class);
        //then
        PromotionDto actual = promotionDtoRowMapper.map(resultSet);
    }
}
