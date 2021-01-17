package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.UserServiceImpl;
import com.epam.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

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

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute(ID);
        String password = req.getParameter(PASSWORD);

        Optional<User> userOptional= userService.getUserById(id);

        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setPassword(password);
        }

        if(!userValidator.isValid(user)){
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, PASSWORD_ERROR_MESSAGE);
            return CommandResult.forward(USER_EDIT_LOCATION);
        } else {
            userService.changePassword(id, password);
            return CommandResult.redirect(INFO_LOCATION);
        }
    }
}
