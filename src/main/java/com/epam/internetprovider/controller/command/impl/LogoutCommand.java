package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code LogoutCommand} the class logs the user out and transition to a index page.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class LogoutCommand implements Command {
    private static final String INDEX_PAGE = "index.jsp";

    /**
     * Execute command logs out using the parameters of HttpServletRequest object
     * and returns CommandResult to a index page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        servletRequest.getSession().invalidate();
        return CommandResult.forward(INDEX_PAGE);
    }
}
