package com.epam.web.dao;

import com.epam.web.entity.Entity;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.RowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code AbstractDao<T extends Entity>} presents basic class data access object.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    private final Connection connection;
    private final RowMapper<T> mapper;

    protected AbstractDao(Connection connection, RowMapper<T> mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    /**
     * Executing query
     *
     * @param query the current query to execute
     * @param params the current params to build query
     */
    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            LOGGER.error("Error with sqlQueryExecute");
            throw new DaoException(e);
        }
    }

    /**
     * Creating statement
     *
     * @param query the current query to execute
     * @param params the current params to build query
     */
    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    /**
     * Executing query for single result
     *
     * @param query the current query to execute
     * @param params the current params to build query
     */
    @Override
    public Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new DaoException("More then one record found");
        } else {
            return Optional.empty();
        }
    }
}

