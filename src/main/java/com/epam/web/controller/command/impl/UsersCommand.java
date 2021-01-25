package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.command.PageController;
import com.epam.web.entity.dto.UserDto;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserDtoService;
import com.epam.web.service.UserService;
import com.epam.web.service.impl.UserDtoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The {@code UsersCommand} the class represents transition to a page with users.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UsersCommand implements Command {
    private static final String USERS_PAGE = "/WEB-INF/view/pages/admin-users.jsp";
    private static final String USERS = "users";
    private static final int RECORDS_ON_PAGE = 5;
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private final UserDtoService userDtoService;
    private final UserService userService;
    private final PageController pageController;

    public UsersCommand(UserDtoServiceImpl userDtoService, UserService userService, PageController pageController) {
        this.userDtoService = userDtoService;
        this.userService = userService;
        this.pageController = pageController;
    }

    /**
     * Execute command to show users using the parameters of HttpServletRequest object
     * and returns CommandResult to users page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        int currentPage = pageController.getCurrentPage(servletRequest);
        servletRequest.setAttribute(CURRENT_PAGE, currentPage);

        List<UserDto> userDtoList = userDtoService.getUserDtoForPage((currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);
        servletRequest.setAttribute(USERS, userDtoList);

        int numberOfRecords = userService.getAllUsers().size();
        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);
        servletRequest.setAttribute(NO_OF_PAGES, numberPages);

        return CommandResult.forward(USERS_PAGE);
    }
}
