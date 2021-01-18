package com.epam.web.controller.command;

import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code Command} interface represents command performed on the server side of the application.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface Command {

    /**
     * Execute command using the parameters of HttpServletRequest object
     * and returns CommandResult object with further instructions to navigate to the page
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException;
}