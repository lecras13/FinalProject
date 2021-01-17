package com.epam.web.dao.helper;

import com.epam.web.controller.connection.ConnectionPool;
import com.epam.web.controller.connection.ProxyConnection;
import com.epam.web.dao.*;
import com.epam.web.dao.impl.*;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(DaoHelper.class);

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        LOGGER.info("USER_HELPER in load");
        return new UserDaoImpl(connection);
    }

    public TariffPlansDao createTariffDao() {
        LOGGER.info("Tariff_HELPER in load");
        return new TariffPlansDaoImpl(connection);
    }

    public PromotionDao createPromotionDao() {
        LOGGER.info("Promotion_HELPER in load");
        return new PromotionDaoImpl(connection);
    }

    public PromotionDtoDao createPromotionDtoDao() {
        LOGGER.info("PromotionDto_HELPER in load");
        return new PromotionDtoDaoImpl(connection);
    }

    public UserDtoDao createUserDtoDao() {
        LOGGER.info("PromotionDto_HELPER in load");
        return new UserDtoDaoImpl(connection);
    }

    public PaymentDao createPaymentDao() {
        LOGGER.info("Promotion_HELPER in load");
        return new PaymentDaoImpl(connection);
    }

    @Override
    public void close() {
        connection.close();
    }

    public void startTransAction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("SQLException when starting transaction!");
            throw new DaoException(e);
        }
    }

    public void rollback() throws ServiceException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("SQLException when starting rollback!");
            throw new ServiceException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("SQLException when starting commit!");
            throw new DaoException(e);
        }
    }
}



