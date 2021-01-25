package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.entity.Role;
import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.TariffPlanServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code UserEditCommand} the class represents user edit command.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserEditCommand implements Command {
    private static final String USER_EDIT_LOCATION = "/WEB-INF/view/pages/user-edit.jsp";
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String ROLE = "role";
    private static final String SESSION_ROLE = "userRole";
    private static final String TOTAL_AMOUNT = "total_amount";
    private static final String STATUS_BLOCK = "status_block";
    private static final String TRAFFIC = "traffic";
    private static final String DISCOUNT = "discount";
    private static final String TARIFF_ID = "tariff_id";
    private static final String PROMOTION_ID = "promotion_id";
    private static final String TARIFF_PLANS = "tariffs";

    private final TariffPlanServiceImpl tariffPlanService;

    public UserEditCommand(TariffPlanServiceImpl tariffPlanService) {
        this.tariffPlanService = tariffPlanService;
    }

    /**
     * Execute command to edit user-page using the parameters of HttpServletRequest object
     * and returns CommandResult to edit user page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        HttpSession session = servletRequest.getSession();
        Long id = session.getAttribute(SESSION_ROLE).equals(Role.USER) ?
                (Long) session.getAttribute(USER_ID) :
                Long.parseLong(servletRequest.getParameter(ID));
        servletRequest.setAttribute(ID, id);

        String login = servletRequest.getParameter(LOGIN);
        servletRequest.setAttribute(LOGIN, login);

        String firstName = servletRequest.getParameter(FIRST_NAME);
        servletRequest.setAttribute(FIRST_NAME, firstName);

        String lastName = servletRequest.getParameter(LAST_NAME);
        servletRequest.setAttribute(LAST_NAME, lastName);

        Role role = Role.valueOf(servletRequest.getParameter(ROLE));
        servletRequest.setAttribute(ROLE, role);

        Double totalAmount = Double.parseDouble(servletRequest.getParameter(TOTAL_AMOUNT));
        servletRequest.setAttribute(TOTAL_AMOUNT, totalAmount);

        String tariffId = servletRequest.getParameter(TARIFF_ID);
        servletRequest.setAttribute(TARIFF_ID, tariffId);

        String promotionId = servletRequest.getParameter(PROMOTION_ID);
        servletRequest.setAttribute(PROMOTION_ID, promotionId);

        String statusBlock = servletRequest.getParameter(STATUS_BLOCK);
        servletRequest.setAttribute(STATUS_BLOCK, statusBlock);

        Double traffic = Double.parseDouble(servletRequest.getParameter(TRAFFIC));
        servletRequest.setAttribute(TRAFFIC, traffic);

        String discount = servletRequest.getParameter(DISCOUNT);
        servletRequest.setAttribute(DISCOUNT, discount);

        List<TariffPlan> tariffPlans = tariffPlanService.getTariffPlansOnlyActive();
        servletRequest.setAttribute(TARIFF_PLANS, tariffPlans);

        return CommandResult.forward(USER_EDIT_LOCATION);
    }
}