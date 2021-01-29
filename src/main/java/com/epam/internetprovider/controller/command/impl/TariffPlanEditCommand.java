package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;

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
            servletRequest.setAttribute(ID, id);

            String tariffName = servletRequest.getParameter(TARIFF_NAME);
            servletRequest.setAttribute(TARIFF_NAME, tariffName);

            Integer price = Integer.parseInt(servletRequest.getParameter(PRICE));
            servletRequest.setAttribute(PRICE, price);

            String description = servletRequest.getParameter(DESCRIPTION);
            servletRequest.setAttribute(DESCRIPTION, description);

            String status = servletRequest.getParameter(STATUS);
            servletRequest.setAttribute(STATUS, status);
        }
        return CommandResult.forward(TARIFF_EDIT_LOCATION);
    }
}