package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;
import com.epam.internetprovider.entity.User;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.impl.UserServiceImpl;
import com.epam.internetprovider.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The {@code UserSavePasswordCommand} the class represents saving command and transition to a info user page.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserSavePasswordCommand implements Command {
    private static final String INFO_LOCATION = "/controller?command=info";
    private static final String USER_EDIT_LOCATION = "/WEB-INF/view/pages/user-password-edit.jsp";
    private static final String ID = "user_id";
    private static final String PASSWORD = "password";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessagePassword";
    private static final String PASSWORD_ERROR_MESSAGE = "Passwords did not match, or you entered an incorrect value!";

    private final UserServiceImpl userService;
    private final Validator<User> userValidator;

    public UserSavePasswordCommand(UserServiceImpl userService, Validator<User> userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    /**
     * Execute command to save user password using the parameters of HttpServletRequest object
     * and returns CommandResult to info user page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        HttpSession session = servletRequest.getSession();
        Long id = (Long) session.getAttribute(ID);
        String password = servletRequest.getParameter(PASSWORD);

        Optional<User> userOptional = userService.getUserById(id);

        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setPassword(password);
        }

        if (!userValidator.isValid(user)) {
            servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE, PASSWORD_ERROR_MESSAGE);
            return CommandResult.forward(USER_EDIT_LOCATION);
        } else {
            userService.changePassword(id, password);
            return CommandResult.redirect(INFO_LOCATION);
        }
    }
}
