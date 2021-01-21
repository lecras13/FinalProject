package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code TariffPlanEditCommand} the class represents tariff plan edit command.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffPlanEditCommand implements Command {
    private static final String TARIFF_EDIT_LOCATION = "/WEB-INF/view/pages/tariffs-edit.jsp";
    private static final String ID = "tariff_id";
    private static final String TARIFF_NAME = "tariff";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";

    public TariffPlanEditCommand(){
    }

    /**
     * Execute command to edit tariff plan using the parameters of HttpServletRequest object
     * and returns CommandResult to tariff plan edit page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        if (servletRequest.getParameter(ID) != null) {
            Long id = Long.parseLong(servletRequest.getParameter(ID));
            String tariffName = servletRequest.getParameter(TARIFF_NAME);
            Integer price = Integer.parseInt(servletRequest.getParameter(PRICE));
            String description = servletRequest.getParameter(DESCRIPTION);
            String status = servletRequest.getParameter(STATUS);

            servletRequest.setAttribute(ID, id);
            servletRequest.setAttribute(TARIFF_NAME, tariffName);
            servletRequest.setAttribute(PRICE, price);
            servletRequest.setAttribute(DESCRIPTION, description);
            servletRequest.setAttribute(STATUS, status);
        }
        return CommandResult.forward(TARIFF_EDIT_LOCATION);
    }
}