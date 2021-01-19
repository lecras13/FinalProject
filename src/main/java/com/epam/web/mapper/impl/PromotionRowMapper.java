package com.epam.web.mapper.impl;

import com.epam.web.entity.Promotion;
import com.epam.web.entity.TariffPlan;
import com.epam.web.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * The {@code PromotionRowMapper} class represents assembling an {@link Promotion} from the database.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionRowMapper implements RowMapper<Promotion> {
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String DESCRIPTION = "description";
    private static final String TARIFF_ID = "tariff_id";
    private static final String NEW_PRICE = "new_price";

    /**
     * Assembling an {@link Promotion} from the database {@link ResultSet}.
     *
     * @param resultSet represents the resulting dataset and provides the
     *                  application with line-by-line access to query results.
     * @throws SQLException the sql exception
     */
    @Override
    public Promotion map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String promotionName = resultSet.getString(PROMOTION_NAME);
        Date startDate = resultSet.getDate(START_DATE);
        Date endDate = resultSet.getDate(END_DATE);
        String description = resultSet.getString(DESCRIPTION);
        Long tariffPlanId = resultSet.getLong(TARIFF_ID);
        Integer newPrice = resultSet.getInt(NEW_PRICE);
        return new Promotion(id, promotionName, startDate, endDate, description, tariffPlanId, newPrice);
    }
}
