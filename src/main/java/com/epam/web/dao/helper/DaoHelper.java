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

/**
 * The {@code DaoHelper} presents class managing different
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class DaoHelper implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger(DaoHelper.class);

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    /**
     * Provides UserDao instance
     *
     * @return {@link UserDao} instance
     */
    public UserDao createUserDao() {
        LOGGER.info("USER_HELPER in load");
        return new UserDaoImpl(connection);
    }

    /**
     * Provides TariffPlansDao instance
     *
     * @return {@link TariffPlansDao} instance
     */
    public TariffPlansDao createTariffDao() {
        LOGGER.info("Tariff_HELPER in load");
        return new TariffPlansDaoImpl(connection);
    }

    /**
     * Provides PromotionDao instance
     *
     * @return {@link PromotionDao} instance
     */
    public PromotionDao createPromotionDao() {
        LOGGER.info("Promotion_HELPER in load");
        return new PromotionDaoImpl(connection);
    }

    /**
     * Provides PromotionDtoDao instance
     *
     * @return {@link PromotionDtoDao} instance
     */
    public PromotionDtoDao createPromotionDtoDao() {
        LOGGER.info("PromotionDto_HELPER in load");
        return new PromotionDtoDaoImpl(connection);
    }

    /**
     * Provides UserDtoDao instance
     *
     * @return {@link UserDtoDao} instance
     */
    public UserDtoDao createUserDtoDao() {
        LOGGER.info("PromotionDto_HELPER in load");
        return new UserDtoDaoImpl(connection);
    }

    /**
     * Provides PaymentDao instance
     *
     * @return {@link PaymentDao} instance
     */
    public PaymentDao createPaymentDao() {
        LOGGER.info("Promotion_HELPER in load");
        return new PaymentDaoImpl(connection);
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     */
    @Override
    public void close() {
        connection.close();
    }

    /**
     * Each SQL expression is treated as a separate transaction
     *
     * @throws DaoException in case of errors and  for checked exceptions of lower application levels
     */
    public void startTransAction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("SQLException when starting transaction!");
            throw new DaoException(e);
        }
    }

    /**
     * Cancels all changes made since the start of the transaction
     *
     * @throws ServiceException in case of errors and  for checked exceptions of lower application levels
     */
    public void rollback() throws ServiceException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("SQLException when starting rollback!");
            throw new ServiceException(e);
        }
    }

    /**
     * Confirmation that the transaction was completed successfully
     *
     * @throws DaoException in case of errors and  for checked exceptions of lower application levels
     */
    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("SQLException when starting commit!");
            throw new DaoException(e);
        }
    }
}



