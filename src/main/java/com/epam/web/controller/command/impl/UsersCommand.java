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

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int currentPage = pageController.getCurrentPage(request);
        List<UserDto> userDtoList = userDtoService.getUserDtoForPage((currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);

        int numberOfRecords = userService.getAllUsers().size();
        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);

        request.setAttribute(NO_OF_PAGES, numberPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(USERS, userDtoList);
        return CommandResult.forward(USERS_PAGE);
    }
}
