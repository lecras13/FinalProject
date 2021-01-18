package com.epam.web.controller.connection;

import com.epam.web.exception.ConnectionPoolException;
import com.epam.web.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code ConnectionPool} represents pool of connections with a given number.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int POOL_SIZE = 10;
    private static final ReentrantLock LOCKER = new ReentrantLock();
    private static final ReentrantLock LOCKER_FOR_CLASS = new ReentrantLock();

    private static ConnectionPool instance = null;
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private final Queue<ProxyConnection> availableConnection;
    private final Queue<ProxyConnection> connectionsInUse;

    /**
     * @return instance of the pool.
     *
     * @throws ConnectionPoolException the sql exception
     */
    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (!isCreated.get()) {
            LOCKER_FOR_CLASS.lock();
            try {
                if (!isCreated.get()) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                    LOGGER.info("Pool Created successfully.");
                }
            } catch (DaoException e) {
                LOGGER.error("DaoException when creation connectionPool!");
                throw new ConnectionPoolException(e.getMessage(), e);
            } finally {
                LOCKER_FOR_CLASS.unlock();
            }
        }
        return instance;
    }

    /**
     * Constructor creating a certain number of connections
     *
     * @return instance of the pool.
     */
    private ConnectionPool() throws DaoException {
        availableConnection = new ArrayDeque<>(POOL_SIZE);
        connectionsInUse = new ArrayDeque<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.createConnection();
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            availableConnection.add(proxyConnection);
        }
    }

    /**
     * Release connection.
     *
     * @param proxyConnection the connection
     */
    public void releaseConnection(ProxyConnection proxyConnection) {
        LOCKER.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnection.offer(proxyConnection);
                semaphore.release();
            }
        } finally {
            LOCKER.unlock();
        }
    }

    /**
     * @return connection for  the connection pool.
     */
    public ProxyConnection getConnection() {
        LOCKER.lock();
        try {
            semaphore.release();
            ProxyConnection proxyConnection = availableConnection.poll();
            connectionsInUse.offer(proxyConnection);
            LOGGER.info("Connection has been given!");
            return proxyConnection;
        } finally {
            LOCKER.unlock();
        }
    }

    /**
     * Destroying the connection pool
     *
     * @throws ConnectionPoolException the sql exception
     */
    public void destroyPool() {
        availableConnection.addAll(connectionsInUse);
        connectionsInUse.clear();
        try {
            for (ProxyConnection connection : availableConnection) {
                connection.shutDown();
            }
        } catch (SQLException e) {
            LOGGER.info("Connection pool has been destroyed!");
            throw new ConnectionPoolException();
        }
    }
}

