package com.epam.web.dao;

import com.epam.web.entity.Entity;
import com.epam.web.exception.DaoException;
import com.epam.web.extractor.EntityExtractor;
import com.epam.web.mapper.RowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The {@code AbstractDaoPersistent<T extends Entity>} presents basic Data access object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public abstract class AbstractDaoPersistent<T extends Entity> extends AbstractDao<T> implements DaoPersistent<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDaoPersistent.class);
    private final static String SELECT_BY_ID = "SELECT * FROM %s WHERE id = ?";
    private final static String SELECT_ALL = "SELECT * FROM %s";
    private final static String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";

    private final EntityExtractor<T> entityExtractor;
    private final String table;
    private final String saveQuery;
    private final String updateQuery;

    public AbstractDaoPersistent(Connection connection, RowMapper<T> mapper, EntityExtractor<T> entityExtractor, String table, String saveQuery, String updateQuery) {
        super(connection, mapper);
        this.entityExtractor = entityExtractor;
        this.table = table;
        this.saveQuery = saveQuery;
        this.updateQuery = updateQuery;
    }

    /**
     * Getting {@link List} of persistent objects
     *
     * @return found {@link List} of persistent object
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    public List<T> getAll() throws DaoException {
        String query = String.format(SELECT_ALL, table);
        return executeQuery(query);
    }

    /**
     * Get the persistent object by id
     *
     * @param id the current id
     * @return found {@link Optional} persistent object
     */
    @Override
    public Optional<T> getById(Long id) throws DaoException {
        String query = String.format(SELECT_BY_ID, table);
        return executeForSingleResult(query, id);
    }

    /**
     * Update the parameter
     *
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    protected void saveParametersItem(String query, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(query, params)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException when saving parameters!");
            throw new DaoException();
        }
    }

    /**
     * Save or update the persistent object
     *
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public void save(T item) throws DaoException {
        Map<String, Object> parsed = entityExtractor.parse(item);
        Object[] values = (parsed.values()).toArray();
        try {
            if (item.getId() == null) {
                createStatement(saveQuery, values).executeUpdate();
            } else {
                createStatement(updateQuery, values).executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException when saving entity!");
            throw new DaoException(e);
        }
    }

    /**
     * Delete the  persistent object by id
     *
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public void removeById(Long id) throws DaoException {
        try {
            String query = String.format(DELETE_BY_ID, table);
            createStatement(query, id).executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException when removing entity!");
            throw new DaoException(e);
        }
    }
}

