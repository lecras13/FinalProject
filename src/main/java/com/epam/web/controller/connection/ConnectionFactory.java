package com.epam.web.controller.connection;

import com.epam.web.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The {@code ConnectionFactory} establishes a connection to the database.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class ConnectionFactory {
    private final static String DB = "database";
    private final static String DB_URL = "db.url";
    private final static String DB_USER = "db.user";
    private final static String DB_PASSWORD = "db.password";
    private final String url;
    private final String user;
    private final String pass;


    ConnectionFactory() {
        ResourceBundle resource = ResourceBundle.getBundle(DB);
        url = resource.getString(DB_URL);
        user = resource.getString(DB_USER);
        pass = resource.getString(DB_PASSWORD);
    }

    /**
     * Creating connection to database by {@code DriverManager}
     *
     * @return connection to database
     * @throws DaoException in case of errors by resource parameters
     */
    public Connection createConnection() throws DaoException {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
