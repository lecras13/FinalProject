package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.Promotion;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionService;
import com.epam.web.service.impl.TariffPlanServiceImpl;
import com.epam.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PromotionSaveCommand implements Command {
    private static final String PROMOTIONS_PAGE = "/controller?command=promotions";
    private static final String PROMOTIONS_EDIT_LOCATION = "/WEB-INF/view/pages/promotions-edit.jsp";
    private static final String ID = "id";
    private static final String PROMOTION_NAME = "promotion-name";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String START_DATE = "start-date";
    private static final String END_DATE = "end-date";
    private static final String DESCRIPTION = "description";
    private static final String TARIFF_ID = "select_tariff";
    private static final String NEW_PRICE = "new-price";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessageData";
    private static final String ERROR_MESSAGE = "Wrong data!";
    private static final String TARIFF_PLANS = "tariffs";
    private static final String TIME_ZONE = "Europe/London";
    private static final String EMPTY_STRING = "";

    private final PromotionService promotionService;
    private final TariffPlanServiceImpl tariffPlanService;

    public PromotionSaveCommand(PromotionService promotionService, TariffPlanServiceImpl tariffPlanService){
        this.promotionService = promotionService;
        this.tariffPlanService = tariffPlanService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        Date startDate;
        Date endDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        Long id = request.getParameter(ID).equals(EMPTY_STRING) ? null : Long.parseLong(request.getParameter(ID));
        String promotionName = request.getParameter(PROMOTION_NAME);
        try {
            startDate = simpleDateFormat.parse(request.getParameter(START_DATE));
            endDate = simpleDateFormat.parse(request.getParameter(END_DATE));
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
        String description = request.getParameter(DESCRIPTION);
        Long tariffPlanId = Long.parseLong(request.getParameter(TARIFF_ID));
        Integer newPrice = Integer.parseInt(request.getParameter(NEW_PRICE));

        try {
            promotionService.savePromotion(id, promotionName, startDate, endDate, description, tariffPlanId, newPrice);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            List<TariffPlan> tariffPlans = tariffPlanService.getTariffPlans();
            request.setAttribute(TARIFF_PLANS, tariffPlans);
            return CommandResult.forward(PROMOTIONS_EDIT_LOCATION);
        }
        return CommandResult.redirect(PROMOTIONS_PAGE);
    }
}
