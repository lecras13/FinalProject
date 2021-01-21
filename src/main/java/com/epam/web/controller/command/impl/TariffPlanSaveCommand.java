package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code TariffPlanSaveCommand} the class represents saving command and transition to a page with tariff plans.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffPlanSaveCommand implements Command {
    private static final String TARIFF_PLANS_PAGE = "/controller?command=tariffs";
    private static final String TARIFF_EDIT_LOCATION = "/WEB-INF/view/pages/tariffs-edit.jsp";
    private static final String ID = "id";
    private static final String TARIFF_NAME = "tariff";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String STATUS = "status";
    private static final String EMPTY_STRING = "";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessageData";
    private static final String ERROR_MESSAGE = "Wrong data!";

    private final TariffPlanService service;

    public TariffPlanSaveCommand(TariffPlanService service) {
        this.service = service;
    }

    /**
     * Execute command to save tariff plan using the parameters of HttpServletRequest object
     * and returns CommandResult to tariff plans page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Long id = servletRequest.getParameter(ID).equals(EMPTY_STRING) ? null : Long.parseLong(servletRequest.getParameter(ID));

        String tariffName = servletRequest.getParameter(TARIFF_NAME);
        Integer price = Integer.parseInt(servletRequest.getParameter(PRICE));
        String description = servletRequest.getParameter(DESCRIPTION);
        Boolean status =Boolean.valueOf(servletRequest.getParameter(STATUS));

        try {
            service.saveTariffPlan(id, tariffName, price, description, status);
        } catch (ServiceException e) {
            servletRequest.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            return CommandResult.forward(TARIFF_EDIT_LOCATION);
        }
        return CommandResult.redirect(TARIFF_PLANS_PAGE);
    }
}
