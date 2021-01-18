package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code TariffPlanDeleteCommand} the class represents tariff plan deleting command.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffPlanDeleteCommand implements Command {
    private static final String TARIFF_PLANS_PAGE = "/controller?command=tariffs";
    private static final String ID = "tariff_id";

    private final TariffPlanService service;

    public TariffPlanDeleteCommand(TariffPlanService service) {
        this.service = service;
    }

    /**
     * Execute command to delete tariff plan using the parameters of HttpServletRequest object
     * and returns CommandResult to tariff plans page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        Long id = Long.parseLong(servletRequest.getParameter(ID));
        service.deleteTariffPlan(id);
        return CommandResult.redirect(TARIFF_PLANS_PAGE);
    }
}