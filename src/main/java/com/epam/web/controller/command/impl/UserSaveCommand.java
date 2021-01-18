package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The {@code TariffPlanSaveCommand} the class represents saving command and transition to a page with tariff plans.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserSaveCommand implements Command {
    private static final String LIST_USERS_LOCATION = "/controller?command=users";
    private static final String INFO_LOCATION = "/controller?command=info";
    private final static String SESSION_ROLE = "userRole";
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String STATUS_BLOCK = "status_block";
    private static final String DISCOUNT = "discount";
    private static final String TARIFF_SELECT = "select_tariff";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessageData";
    private static final String ERROR_MESSAGE = "Wrong data!";

    private final Validator<User> userValidator;
    private final UserServiceImpl userService;

    public UserSaveCommand(Validator<User> userValidator, UserServiceImpl userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    /**
     * Execute command to save user using the parameters of HttpServletRequest object
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
        Long id = session.getAttribute(SESSION_ROLE).equals(Role.USER) ?
                (Long) session.getAttribute(USER_ID) :
                Long.parseLong(servletRequest.getParameter(ID));

        String login = servletRequest.getParameter(LOGIN);
        String firstName = servletRequest.getParameter(FIRST_NAME);
        String lastName = servletRequest.getParameter(LAST_NAME);
        Boolean statusBlock = Boolean.valueOf(servletRequest.getParameter(STATUS_BLOCK));
        Integer discount = Integer.parseInt(servletRequest.getParameter(DISCOUNT));
        Long tariffId = Long.parseLong(servletRequest.getParameter(TARIFF_SELECT));

        Optional<User> userOptional = userService.getUserById(id);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setLogin(login);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setStatusBlock(statusBlock);
            user.setDiscount(discount);
            user.setTariffId(tariffId);
        }
        if (!userValidator.isValid(user)) {
            servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            return CommandResult.forward(INFO_LOCATION);
        } else {
            Object[] paramAdmin = new Object[]{login, firstName, lastName, statusBlock, discount, tariffId, id};
            Object[] paramUser = new Object[]{login, firstName, lastName, tariffId, id};

            Role mainRole = (Role) session.getAttribute(SESSION_ROLE);
            if (mainRole.equals(Role.ADMIN)) {
                userService.updateUserByAdmin(paramAdmin);
                return CommandResult.forward(LIST_USERS_LOCATION);
            } else {
                userService.updateUserByUser(paramUser);
                return CommandResult.forward(INFO_LOCATION);
            }
        }
    }
}
