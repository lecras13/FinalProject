package com.epam.internetprovider.dao.impl;

import com.epam.internetprovider.dao.AbstractDaoPersistent;
import com.epam.internetprovider.dao.TariffPlansDao;
import com.epam.internetprovider.entity.TariffPlan;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.extractor.impl.TariffPlanExtractor;
import com.epam.internetprovider.mapper.impl.TariffRowMapper;

import java.sql.Connection;
import java.util.List;

/**
 * The {@code TariffPlansDaoImpl} presents tariff plans dao implementation
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffPlansDaoImpl extends AbstractDaoPersistent<TariffPlan> implements TariffPlansDao {
    private static final String TABLE_NAME = "tariff_plans";
    private static final String UPDATE = "UPDATE tariff_plans SET tariff_name=?, price=?, description=?, status=? WHERE id=?";
    private static final String SAVE = "INSERT INTO tariff_plans(tariff_name, price, description, status, id) VALUES(?, ?, ?, ?, ?)";
    private static final String GET_FOR_TABLE = "SELECT * FROM tariff_plans limit ?, ?";
    private static final String GET_FOR_TABLE_ACTIVE = "SELECT * FROM tariff_plans WHERE status=0 limit ?, ?";
    private static final String GET_ACTIVE = "SELECT * FROM tariff_plans WHERE status=0";

    public TariffPlansDaoImpl(Connection connection) {
        super(connection, new TariffRowMapper(), new TariffPlanExtractor(), TABLE_NAME, SAVE, UPDATE);
    }

    /**
     * Get the tariff plans list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of tariff plans for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_FOR_TABLE, firstRow, rowCount);
    }

    /**
     * Get the tariff plans only active list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of tariff plans for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<TariffPlan> getTariffPlansOnlyActiveForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_FOR_TABLE_ACTIVE, firstRow, rowCount);
    }

    /**
     * Get tariff plans only active list
     *
     * @return found {@link List} of tariff plans active only
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<TariffPlan> getTariffPlansOnlyActive() throws DaoException {
        return executeQuery(GET_ACTIVE);
    }
}