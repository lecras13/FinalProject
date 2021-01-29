package com.epam.internetprovider.controller.connection;

import com.epam.internetprovider.exception.ConnectionPoolException;
import com.epam.internetprovider.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The {@code ConnectionFactory} establishes a connection to the database.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class ConnectionFactory {
    private final static String DB = "database.properties";
    private final static String DB_URL = "db.url";
    private final static String DB_USER = "db.user";
    private final static String DB_PASSWORD = "db.password";
    private final String url;
    private final String user;
    private final String pass;


    ConnectionFactory() {
        Properties properties = getProperties();
        url = properties.getProperty(DB_URL);
        user = properties.getProperty(DB_USER);
        pass = properties.getProperty(DB_PASSWORD);
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

    /**
     * Reading properties to connection to database
     *
     * @return properties connection to database
     * @throws ConnectionPoolException in case of errors with reading properties db
     */
    private Properties getProperties() throws ConnectionPoolException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(DB)) {
            if (input == null) {
                throw new ConnectionPoolException();
            }
            properties.load(input);
        } catch (IOException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        return properties;
    }
}
