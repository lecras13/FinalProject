package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TariffPlanDeleteCommand implements Command {
    private static final String TARIFF_PLANS_PAGE = "/controller?command=tariffs";
    private static final String ID = "tariff_id";

    private final TariffPlanService service;

    public TariffPlanDeleteCommand(TariffPlanService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long id = Long.parseLong(request.getParameter(ID));
        service.deleteTariffPlan(id);
        return CommandResult.redirect(TARIFF_PLANS_PAGE);
    }
}