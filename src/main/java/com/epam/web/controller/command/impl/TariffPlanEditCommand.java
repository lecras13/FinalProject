package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TariffPlanEditCommand implements Command {
    private static final String TARIFF_EDIT_LOCATION = "/WEB-INF/view/pages/tariffs-edit.jsp";
    private static final String ID = "tariff_id";
    private static final String TARIFF_NAME = "tariff";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";

    public TariffPlanEditCommand() {
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {
        if (request.getParameter(ID) != null) {
            Long id = Long.parseLong(request.getParameter(ID));
            String tariffName = request.getParameter(TARIFF_NAME);
            Integer price = Integer.parseInt(request.getParameter(PRICE));
            String description = request.getParameter(DESCRIPTION);

            request.setAttribute(ID, id);
            request.setAttribute(TARIFF_NAME, tariffName);
            request.setAttribute(PRICE, price);
            request.setAttribute(DESCRIPTION, description);
        }
        return CommandResult.forward(TARIFF_EDIT_LOCATION);
    }
}