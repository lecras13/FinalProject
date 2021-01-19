package com.epam.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code RowMapper<T>} interface represents assembling an element from the database.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface RowMapper<T> {

    /**
     * Assembling an specific element from the database {@link ResultSet}.
     *
     * @param resultSet represents the resulting dataset and provides the
     *                  application with line-by-line access to query results.
     * @throws SQLException the sql exception
     */
    T map(ResultSet resultSet) throws SQLException;
}
