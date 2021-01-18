package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code UserEditPasswordCommand} the class represents user password edit command.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserEditPasswordCommand implements Command {
    private static final String USER_EDIT_LOCATION = "/WEB-INF/view/pages/user-password-edit.jsp";

    public UserEditPasswordCommand() {
    }

    /**
     * Execute command to edit password user using the parameters of HttpServletRequest object
     * and returns CommandResult to edit password form page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return CommandResult.forward(USER_EDIT_LOCATION);
    }
}
