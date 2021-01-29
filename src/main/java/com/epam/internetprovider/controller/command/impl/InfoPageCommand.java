package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;
import com.epam.internetprovider.entity.Promotion;
import com.epam.internetprovider.entity.Role;
import com.epam.internetprovider.entity.TariffPlan;
import com.epam.internetprovider.entity.User;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.PromotionService;
import com.epam.internetprovider.service.TariffPlanService;
import com.epam.internetprovider.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The {@code InfoPageCommand} the class represents a transition to a page with information about the user.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class InfoPageCommand implements Command {
    private static final String INFO_LOCATION = "/WEB-INF/view/pages/info.jsp";
    private static final String ID = "user_id";
    private static final String USER = "user";
    private static final String SESSION_ROLE = "userRole";
    private static final String TARIFF_NAME = "tariff_name";
    private static final String PROMOTION_NAME = "promotion_name";
    private static final String PROMOTION_NONE = "---";

    private final UserService userService;
    private final TariffPlanService tariffPlanService;
    private final PromotionService promotionService;

    public InfoPageCommand(UserService userService, TariffPlanService tariffPlanService, PromotionService promotionService) {
        this.userService = userService;
        this.tariffPlanService = tariffPlanService;
        this.promotionService = promotionService;
    }

    /**
     * Execute command to get info using the parameters of HttpServletRequest object
     * and returns CommandResult to a page with information about the user.
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
                (Long) session.getAttribute(ID) :
                Long.parseLong(servletRequest.getParameter(ID));

        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Long tariffId = user.getTariffId();
            Long promotionId = user.getPromotionId();
            servletRequest.setAttribute(USER, user);

            Optional<TariffPlan> tariffPlanOptional = tariffPlanService.getTariffPlanById(tariffId);
            if (tariffPlanOptional.isPresent()) {
                TariffPlan tariffPlan = tariffPlanOptional.get();
                String tariffPlanName = tariffPlan.getTariffName();
                servletRequest.setAttribute(TARIFF_NAME, tariffPlanName);
            }

            if (promotionId > 0) {
                Optional<Promotion> promotionOptional = promotionService.getPromotionById(promotionId);
                if (promotionOptional.isPresent()) {
                    Promotion promotion = promotionOptional.get();
                    String promotionName = promotion.getPromotionName();
                    servletRequest.setAttribute(PROMOTION_NAME, promotionName);
                }
            } else {
                servletRequest.setAttribute(PROMOTION_NAME, PROMOTION_NONE);
            }
        }
        return CommandResult.forward(INFO_LOCATION);
    }
}