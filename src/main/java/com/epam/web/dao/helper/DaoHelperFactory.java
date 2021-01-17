package com.epam.web.dao.helper;

import com.epam.web.controller.connection.ConnectionPool;
import com.epam.web.exception.ConnectionPoolException;

public class DaoHelperFactory {
    public DaoHelper create()  {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}


