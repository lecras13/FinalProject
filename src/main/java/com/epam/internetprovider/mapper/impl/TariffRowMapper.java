package com.epam.internetprovider.mapper.impl;

import com.epam.internetprovider.entity.TariffPlan;
import com.epam.internetprovider.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code TariffRowMapper} class represents assembling an {@link TariffPlan} from the database.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffRowMapper implements RowMapper<TariffPlan> {
    private static final String ID = "id";
    private static final String TARIFF_NAME = "tariff_name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";

    /**
     * Assembling an {@link TariffPlan} from the database {@link ResultSet}.
     *
     * @param resultSet represents the resulting dataset and provides the
     *                  application with line-by-line access to query results.
     * @throws SQLException the sql exception
     */
    @Override
    public TariffPlan map(ResultSet resultSet) throws SQLException{
        Long id = resultSet.getLong(ID);
        String tariffName = resultSet.getString(TARIFF_NAME);
        Integer price = resultSet.getInt(PRICE);
        String description = resultSet.getString(DESCRIPTION);
        Boolean block = resultSet.getBoolean(STATUS);
        return new TariffPlan(id, tariffName, price, description, block);
    }
}