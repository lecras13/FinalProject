package com.epam.web.mapper.impl;

import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * The {@code PromotionDtoRowMapper} class represents assembling an {@link PromotionDto} from the database.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDtoRowMapper implements RowMapper<PromotionDto> {
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String DESCRIPTION = "description";
    private static final String NEW_PRICE = "new_price";

    private final RowMapper<TariffPlan> tariffRowMapper;

    public PromotionDtoRowMapper(RowMapper<TariffPlan> tariffRowMapper){
        this.tariffRowMapper = tariffRowMapper;
    }

    /**
     * Assembling an {@link PromotionDto} from the database {@link ResultSet}.
     *
     * @param resultSet represents the resulting dataset and provides the
     *                  application with line-by-line access to query results.
     * @throws SQLException the sql exception
     */
    @Override
    public PromotionDto map(ResultSet resultSet) throws SQLException{
        Long id = resultSet.getLong(ID);
        String promotionName = resultSet.getString(PROMOTION_NAME);
        Date startDate = resultSet.getDate(START_DATE);
        Date endDate = resultSet.getDate(END_DATE);
        String description = resultSet.getString(DESCRIPTION);
        TariffPlan tariffPlan = tariffRowMapper.map(resultSet);
        Integer newPrice = resultSet.getInt(NEW_PRICE);
        return new PromotionDto(id, promotionName, startDate, endDate, description, tariffPlan, newPrice);
    }
}