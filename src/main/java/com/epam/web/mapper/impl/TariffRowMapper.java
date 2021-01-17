package com.epam.web.mapper.impl;

import com.epam.web.entity.TariffPlan;
import com.epam.web.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TariffRowMapper implements RowMapper<TariffPlan> {
    private static final String ID = "id";
    private static final String TARIFF_NAME = "tariff_name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";

    @Override
    public TariffPlan map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String tariffName = resultSet.getString(TARIFF_NAME);
        Integer price = resultSet.getInt(PRICE);
        String description = resultSet.getString(DESCRIPTION);
        return new TariffPlan(id, tariffName, price, description);
    }
}