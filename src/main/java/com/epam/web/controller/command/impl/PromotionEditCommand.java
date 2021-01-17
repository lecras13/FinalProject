package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.TariffPlanServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PromotionEditCommand implements Command {
    private static final String PROMOTIONS_EDIT_LOCATION = "/WEB-INF/view/pages/promotions-edit.jsp";
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String DESCRIPTION = "description";
    private static final String TARIFF_ID = "tariff_id";
    private static final String NEW_PRICE = "new_price";
    private static final String TARIFF_PLANS = "tariffs";

    private final TariffPlanServiceImpl tariffPlanService;

    public PromotionEditCommand(TariffPlanServiceImpl tariffPlanService) {
        this.tariffPlanService = tariffPlanService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if (request.getParameter(ID) != null) {
            Long id = Long.parseLong(request.getParameter(ID));

            String promotionName = request.getParameter(PROMOTION_NAME);
            String startDate = request.getParameter(START_DATE);
            String endDate = request.getParameter(END_DATE);
            String description = request.getParameter(DESCRIPTION);
            String tariffId = request.getParameter(TARIFF_ID);
            String newPrice = request.getParameter(NEW_PRICE);

            request.setAttribute(ID, id);
            request.setAttribute(PROMOTION_NAME, promotionName);
            request.setAttribute(START_DATE, startDate);
            request.setAttribute(END_DATE, endDate);
            request.setAttribute(DESCRIPTION, description);
            request.setAttribute(TARIFF_ID, tariffId);
            request.setAttribute(NEW_PRICE, newPrice);
        }
        List<TariffPlan> tariffPlans = tariffPlanService.getTariffPlans();
        request.setAttribute(TARIFF_PLANS, tariffPlans);

        return CommandResult.forward(PROMOTIONS_EDIT_LOCATION);
    }
}