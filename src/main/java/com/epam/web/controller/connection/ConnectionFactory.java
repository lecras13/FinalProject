package com.epam.web.controller.connection;

import com.epam.web.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private final static String DB = "database";
    private final static String DB_URL = "db.url";
    private final static String DB_USER = "db.user";
    private final static String DB_PASSWORD = "db.password";

    public Connection createConnection() throws DaoException {
        ResourceBundle resource = ResourceBundle.getBundle(DB);
        String url = resource.getString(DB_URL);
        String user = resource.getString(DB_USER);
        String pass = resource.getString(DB_PASSWORD);
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            LOGGER.error("SQL exception when getting with driverManager!");
            throw new DaoException(e);
        }
    }
}
