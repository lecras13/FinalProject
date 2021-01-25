package com.epam.web.dao.helper;

import com.epam.web.controller.connection.ConnectionPool;
import com.epam.web.controller.connection.ProxyConnection;
import com.epam.web.dao.*;
import com.epam.web.dao.impl.*;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.sql.SQLException;

/**
 * The {@code DaoHelper} presents class managing different
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class DaoHelper implements AutoCloseable {
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
        return new UserDaoImpl(connection);
    }

    /**
     * Provides TariffPlansDao instance
     *
     * @return {@link TariffPlansDao} instance
     */
    public TariffPlansDao createTariffDao() {
        return new TariffPlansDaoImpl(connection);
    }

    /**
     * Provides PromotionDao instance
     *
     * @return {@link PromotionDao} instance
     */
    public PromotionDao createPromotionDao() {
        return new PromotionDaoImpl(connection);
    }

    /**
     * Provides PromotionDtoDao instance
     *
     * @return {@link PromotionDtoDao} instance
     */
    public PromotionDtoDao createPromotionDtoDao() {
        return new PromotionDtoDaoImpl(connection);
    }

    /**
     * Provides UserDtoDao instance
     *
     * @return {@link UserDtoDao} instance
     */
    public UserDtoDao createUserDtoDao() {
        return new UserDtoDaoImpl(connection);
    }

    /**
     * Provides PaymentDao instance
     *
     * @return {@link PaymentDao} instance
     */
    public PaymentDao createPaymentDao() {
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
            throw new DaoException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }
}



