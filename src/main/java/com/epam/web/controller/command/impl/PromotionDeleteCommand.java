package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PromotionDeleteCommand implements Command {
    private static final String PROMOTIONS_PLANS_PAGE = "/controller?command=promotions";
    private static final String ID = "id";

    private final PromotionService service;

    public PromotionDeleteCommand(PromotionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String str = request.getParameter(ID);
        Long id = Long.parseLong(str);
        service.deletePromotion(id);
        return CommandResult.forward(PROMOTIONS_PLANS_PAGE);
    }
}
