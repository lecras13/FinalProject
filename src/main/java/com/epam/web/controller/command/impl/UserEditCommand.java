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

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long id = session.getAttribute(SESSION_ROLE).equals(Role.USER) ?
                (Long) session.getAttribute(USER_ID) :
                Long.parseLong(request.getParameter(ID));

        String login = request.getParameter(LOGIN);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        Role role = Role.valueOf(request.getParameter(ROLE));
        Double totalAmount = Double.parseDouble(request.getParameter(TOTAL_AMOUNT));
        String tariffId = request.getParameter(TARIFF_ID);
        String promotionId = request.getParameter(PROMOTION_ID);
        String statusBlock = request.getParameter(STATUS_BLOCK);
        Double traffic = Double.parseDouble(request.getParameter(TRAFFIC));
        String discount = request.getParameter(DISCOUNT);

        request.setAttribute(ID, id);
        request.setAttribute(LOGIN, login);
        request.setAttribute(FIRST_NAME, firstName);
        request.setAttribute(LAST_NAME, lastName);
        request.setAttribute(ROLE, role);
        request.setAttribute(TOTAL_AMOUNT, totalAmount);
        request.setAttribute(STATUS_BLOCK, statusBlock);
        request.setAttribute(TRAFFIC, traffic);
        request.setAttribute(DISCOUNT, discount);
        request.setAttribute(TARIFF_ID, tariffId);
        request.setAttribute(PROMOTION_ID, promotionId);

        List<TariffPlan> tariffPlans = tariffPlanService.getTariffPlans();
        request.setAttribute(TARIFF_PLANS, tariffPlans);

        return CommandResult.forward(USER_EDIT_LOCATION);
    }
}