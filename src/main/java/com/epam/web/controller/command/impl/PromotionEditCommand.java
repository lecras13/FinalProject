package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.TariffPlanServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The {@code PromotionEditCommand} the class represents promotion edit command.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

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

    /**
     * Execute command to edit promotion using the parameters of HttpServletRequest object
     * and returns CommandResult to promotion edit page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        if (servletRequest.getParameter(ID) != null) {
            Long id = Long.parseLong(servletRequest.getParameter(ID));

            String promotionName = servletRequest.getParameter(PROMOTION_NAME);
            String startDate = servletRequest.getParameter(START_DATE);
            String endDate = servletRequest.getParameter(END_DATE);
            String description = servletRequest.getParameter(DESCRIPTION);
            String tariffId = servletRequest.getParameter(TARIFF_ID);
            String newPrice = servletRequest.getParameter(NEW_PRICE);

            servletRequest.setAttribute(ID, id);
            servletRequest.setAttribute(PROMOTION_NAME, promotionName);
            servletRequest.setAttribute(START_DATE, startDate);
            servletRequest.setAttribute(END_DATE, endDate);
            servletRequest.setAttribute(DESCRIPTION, description);
            servletRequest.setAttribute(TARIFF_ID, tariffId);
            servletRequest.setAttribute(NEW_PRICE, newPrice);
        }
        List<TariffPlan> tariffPlans = tariffPlanService.getTariffPlans();
        servletRequest.setAttribute(TARIFF_PLANS, tariffPlans);

        return CommandResult.forward(PROMOTIONS_EDIT_LOCATION);
    }
}