package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The {@code LoginCommand} the class checks the user for certain rights and transition to a appropriate page.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class LoginCommand implements Command {
    private static final String ID = "user_id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MESSAGE_USER_NOT_FOUND = "User not found";
    private static final String ERROR_MESSAGE_USER_BLOCKED = "You are blocked! Contact us!";
    private static final String INDEX_PAGE = "index.jsp";
    private static final String USERS_PAGE = "/controller?command=users";
    private static final String INFO_PAGE = "/controller?command=info";
    private final static String ROLE = "userRole";

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    /**
     * Execute command to login using the parameters of HttpServletRequest object
     * and returns CommandResult to a appropriate page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        String login = servletRequest.getParameter(LOGIN);
        String password = servletRequest.getParameter(PASSWORD);

        Optional<User> guest = service.login(login, password);
        HttpSession session = servletRequest.getSession();

        if (guest.isPresent()) {
            User user = guest.get();
            if (!user.getStatusBlock()) {
                Role role = user.getRole();
                Long id = user.getId();
                session.setAttribute(ID, id);
                session.setAttribute(LOGIN, login);
                session.setAttribute(ROLE, role);
                return redirectByRole(role);
            } else {
                servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE_USER_BLOCKED);
                return CommandResult.forward(INDEX_PAGE);
            }
        } else {
            servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE_USER_NOT_FOUND);
            return CommandResult.forward(INDEX_PAGE);
        }
    }

    private CommandResult redirectByRole(Role role) {
        if (role.equals(Role.ADMIN)) {
            return CommandResult.redirect(USERS_PAGE);
        }
        return CommandResult.redirect(INFO_PAGE);
    }
}
