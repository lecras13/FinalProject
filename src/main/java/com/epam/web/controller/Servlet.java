package com.epam.web.controller;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandFactory;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.connection.ConnectionPool;
import com.epam.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Servlet.class);
    private static final String COMMAND = "command";
    private static final int ERROR_404 = 404;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String commandParam = req.getParameter(COMMAND);
            Command command = CommandFactory.create(commandParam);
            CommandResult commandResult = command.execute(req, resp);
            dispatch(commandResult, req, resp);
        } catch (Exception e) {
            LOGGER.error("Error 404");
            resp.sendError(ERROR_404);
        }
    }

    private void dispatch(final CommandResult commandResult, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean redirect = commandResult.isRedirect();
        String page = commandResult.getCommand();
        if (redirect) {
            resp.sendRedirect(page);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }

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

