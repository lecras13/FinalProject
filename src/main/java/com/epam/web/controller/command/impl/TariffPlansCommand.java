package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.command.PageController;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TariffPlansCommand implements Command {
    private static final String TARIFF_PLANS_PAGE = "/WEB-INF/view/pages/tariff-plans.jsp";
    private static final String TARIFF_PLANS = "tariffs";
    private static final int RECORDS_ON_PAGE = 5;
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private final TariffPlanService tariffPlanService;
    private final PageController pageController;

    public TariffPlansCommand(TariffPlanService service, PageController pageController) {
        this.tariffPlanService = service;
        this.pageController = pageController;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int currentPage = pageController.getCurrentPage(request);
        List<TariffPlan> tariffPlansForPage = tariffPlanService.getTariffPlansForPage((currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);

        int numberOfRecords = tariffPlanService.getTariffPlans().size();
        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);

        request.setAttribute(NO_OF_PAGES, numberPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(TARIFF_PLANS, tariffPlansForPage);
        return CommandResult.forward(TARIFF_PLANS_PAGE);
    }
}
