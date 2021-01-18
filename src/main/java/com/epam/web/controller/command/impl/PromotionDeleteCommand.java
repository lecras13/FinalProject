package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code PromotionDeleteCommand} the class represents promotion deleting command.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDeleteCommand implements Command {
    private static final String PROMOTIONS_PLANS_PAGE = "/controller?command=promotions";
    private static final String ID = "id";

    private final PromotionService service;

    public PromotionDeleteCommand(PromotionService service) {
        this.service = service;
    }

    /**
     * Execute command to delete promotion using the parameters of HttpServletRequest object
     * and returns CommandResult to promotions page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        String str = servletRequest.getParameter(ID);
        Long id = Long.parseLong(str);
        service.deletePromotion(id);
        return CommandResult.forward(PROMOTIONS_PLANS_PAGE);
    }
}
