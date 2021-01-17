package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDaoPersistent;
import com.epam.web.dao.TariffPlansDao;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.DaoException;
import com.epam.web.extractor.impl.TariffPlanExtractor;
import com.epam.web.mapper.impl.TariffRowMapper;

import java.sql.Connection;
import java.util.List;

public class TariffPlansDaoImpl extends AbstractDaoPersistent<TariffPlan> implements TariffPlansDao {
    private static final String TABLE_NAME = "tariff_plans";
    private static final String UPDATE = "UPDATE tariff_plans SET tariff_name=?, price=?, description=? WHERE id=?";
    private static final String SAVE = "INSERT INTO tariff_plans(tariff_name, price, description, id) VALUES(?, ?, ?, ?)";
    private static final String GET_FOR_TABLE = "SELECT * FROM tariff_plans limit ?, ?";

    public TariffPlansDaoImpl(Connection connection) {
        super(connection, new TariffRowMapper(), new TariffPlanExtractor(), TABLE_NAME, SAVE, UPDATE);
    }

    @Override
    public List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_FOR_TABLE, firstRow, rowCount);
    }
}
