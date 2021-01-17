package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TariffPlanSaveCommand implements Command {
    private static final String TARIFF_PLANS_PAGE = "/controller?command=tariffs";
    private static final String TARIFF_EDIT_LOCATION = "/WEB-INF/view/pages/tariffs-edit.jsp";
    private static final String ID = "id";
    private static final String TARIFF_NAME = "tariff";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String EMPTY_STRING = "";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessageData";
    private static final String ERROR_MESSAGE = "Wrong data!";

    private final TariffPlanService service;

    public TariffPlanSaveCommand(TariffPlanService service){
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = request.getParameter(ID).equals(EMPTY_STRING) ? null : Long.parseLong(request.getParameter(ID));

        String tariffName = request.getParameter(TARIFF_NAME);
        Integer price = Integer.parseInt(request.getParameter(PRICE));
        String description = request.getParameter(DESCRIPTION);

        try {
            service.saveTariffPlan(id, tariffName, price, description);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            return CommandResult.forward(TARIFF_EDIT_LOCATION);
        }
        return CommandResult.redirect(TARIFF_PLANS_PAGE);
    }
}
