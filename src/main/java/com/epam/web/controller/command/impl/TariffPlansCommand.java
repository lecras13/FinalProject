package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.command.PageController;
import com.epam.web.entity.Role;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code TariffPlansCommand} the class represents transition to a page tariff plans.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class TariffPlansCommand implements Command {
    private static final String TARIFF_PLANS_PAGE = "/WEB-INF/view/pages/tariff-plans.jsp";
    private static final String TARIFF_PLANS = "tariffs";
    private static final int RECORDS_ON_PAGE = 5;
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private final static String SESSION_ROLE = "userRole";

    private final TariffPlanService tariffPlanService;
    private final PageController pageController;

    public TariffPlansCommand(TariffPlanService service, PageController pageController) {
        this.tariffPlanService = service;
        this.pageController = pageController;
    }

    /**
     * Execute command to show tariff plan using the parameters of HttpServletRequest object
     * and returns CommandResult to tariff plans page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        int currentPage = pageController.getCurrentPage(servletRequest);
        List<TariffPlan> tariffPlansForPage;
        int numberOfRecords;
        HttpSession session = servletRequest.getSession();
        Role mainRole = (Role) session.getAttribute(SESSION_ROLE);

        if ((session.getAttribute(SESSION_ROLE) == null) || (mainRole.equals(Role.USER))) {
            tariffPlansForPage = tariffPlanService.getTariffPlansOnlyActiveForPage((currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);
            numberOfRecords = tariffPlanService.getTariffPlansOnlyActive().size();
        } else {
            tariffPlansForPage = tariffPlanService.getTariffPlansForPage((currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);
            numberOfRecords = tariffPlanService.getTariffPlans().size();
        }

        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);

        servletRequest.setAttribute(NO_OF_PAGES, numberPages);
        servletRequest.setAttribute(CURRENT_PAGE, currentPage);
        servletRequest.setAttribute(TARIFF_PLANS, tariffPlansForPage);
        return CommandResult.forward(TARIFF_PLANS_PAGE);
    }
}
