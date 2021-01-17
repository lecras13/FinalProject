package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserEditPasswordCommand implements Command {
    private static final String USER_EDIT_LOCATION = "/WEB-INF/view/pages/user-password-edit.jsp";

    public UserEditPasswordCommand() {
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {
        return CommandResult.forward(USER_EDIT_LOCATION);
    }
}
