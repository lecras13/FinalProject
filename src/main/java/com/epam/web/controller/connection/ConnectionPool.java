package com.epam.web.controller.connection;

import com.epam.web.exception.ConnectionPoolException;
import com.epam.web.exception.DaoException;

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
    private static final int POOL_SIZE = 10;
    private static final ReentrantLock CONNECTION_LOCK = new ReentrantLock();
    private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();

    private static ConnectionPool instance = null;
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private final Queue<ProxyConnection> availableConnection;
    private final Queue<ProxyConnection> connectionsInUse;
    ConnectionFactory connectionFactory = new ConnectionFactory();

    /**
     * @return instance of the pool.
     * @throws ConnectionPoolException the sql exception
     */
    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (!isCreated.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!isCreated.get()) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } catch (DaoException e) {
                throw new ConnectionPoolException(e.getMessage(), e);
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Constructor creating a certain number of connections
     */
    private ConnectionPool() throws DaoException {
        availableConnection = new ArrayDeque<>(POOL_SIZE);
        connectionsInUse = new ArrayDeque<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
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
        CONNECTION_LOCK.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnection.offer(proxyConnection);
                semaphore.release();
            }
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    /**
     * @return connection for  the connection pool.
     */
    public ProxyConnection getConnection() {
        CONNECTION_LOCK.lock();
        try {
            semaphore.release();
            ProxyConnection proxyConnection = availableConnection.poll();
            connectionsInUse.offer(proxyConnection);
            return proxyConnection;
        } finally {
            CONNECTION_LOCK.unlock();
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
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }
}

