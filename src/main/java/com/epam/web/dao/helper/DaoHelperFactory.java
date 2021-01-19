package com.epam.web.dao.helper;

import com.epam.web.controller.connection.ConnectionPool;
import com.epam.web.exception.ConnectionPoolException;

/**
 * The {@code DaoHelperFactory} presents class helper to connect a database
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class DaoHelperFactory {
    /**
     * Creating dao helper with connection instance
     *
     * @return DaoHelper object for managing different data access object
     */
    public DaoHelper create()  {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}


