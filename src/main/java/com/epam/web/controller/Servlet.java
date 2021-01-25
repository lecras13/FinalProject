package com.epam.web.controller;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandFactory;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.connection.ConnectionPool;
import com.epam.web.exception.ConnectionPoolException;
import com.epam.web.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code Servlet} represents main controller.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class Servlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Servlet.class);
    private static final String COMMAND = "command";
    private static final int ERROR_404 = 404;

    /**
     * Execute method doPost
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @throws IOException thrown for checked exceptions of lower application levels
     */
    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        process(servletRequest, servletResponse);
    }

    /**
     * Execute method doGet
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @throws IOException thrown for checked exceptions of lower application levels
     */
    @Override
    protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        process(servletRequest, servletResponse);
    }

    /**
     * Method presents creating {@link Command} and executing it
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @throws IOException thrown for checked exceptions of lower application levels
     */
    private void process(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        try {
            String commandParam = servletRequest.getParameter(COMMAND);
            Command command = CommandFactory.create(commandParam);
            CommandResult commandResult = command.execute(servletRequest, servletResponse);
            dispatch(commandResult, servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            servletResponse.sendError(ERROR_404);
        }
    }

    /**
     * Method presents transition to next page by {@link CommandResult} type redirect
     *
     * @param commandResult {@link CommandResult} object from the received command
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @throws IOException thrown for checked exceptions of lower application levels
     */
    private void dispatch(final CommandResult commandResult, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        boolean redirect = commandResult.isRedirect();
        String page = commandResult.getCommand();
        if (redirect) {
            servletResponse.sendRedirect(page);
        } else {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(page);
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    /**
     * Destroying connection pool and ending servlet work
     *
     */
    public void destroy() {
        super.destroy();
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Error destroying connectionPool!");
            e.printStackTrace();
        }
    }
}

