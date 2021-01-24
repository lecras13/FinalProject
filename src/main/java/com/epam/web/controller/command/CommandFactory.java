package com.epam.web.controller.command;

import com.epam.web.controller.command.impl.*;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.impl.*;
import com.epam.web.validator.PaymentValidator;
import com.epam.web.validator.PromotionValidator;
import com.epam.web.validator.TariffPlanValidator;
import com.epam.web.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code CommandFactory} represents command control.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class CommandFactory {
    private static final String MAIN_LOCATION = "WEB-INF/view/pages/main.jsp";

    private static final String MAIN = "main";
    private static final String LOGOUT = "logout";
    private static final String LOGIN = "login";
    private static final String USERS = "users";

    private static final String USER_INFO = "info";
    private static final String USER_EDIT = "user-edit";
    private static final String USER_SAVE = "user-save";
    private static final String USER_PASSWORD_EDIT = "user-password-edit";
    private static final String USER_PASSWORD_SAVE = "user-password-save";


    private static final String TARIFF_PLANS = "tariffs";
    private static final String TARIFF_PLANS_SAVE = "tariffs-save";
    private static final String TARIFF_PLANS_EDIT = "tariffs-edit";

    private static final String PROMOTIONS = "promotions";
    private static final String PROMOTIONS_SAVE = "promotions-save";
    private static final String PROMOTIONS_EDIT = "promotions-edit";

    private static final String PAYMENTS_HISTORY = "payments-history";
    private static final String PAYMENT_PAY = "payment-pay";
    private static final String PAYMENT = "payment";

    /**
     * Creating command based on request.
     *
     * @param command the current command
     */
    public static Command create(String command) {
        switch (command) {
            case PAYMENTS_HISTORY:
                return new PaymentHistoryCommand(new PaymentServiceImpl(new DaoHelperFactory(), new PaymentValidator()), new PageController());
            case PAYMENT_PAY:
                return new PayCommand(new PaymentServiceImpl(new DaoHelperFactory(), new PaymentValidator()));
            case PAYMENT:
                return new PaymentFormCommand();

            case PROMOTIONS_EDIT:
                return new PromotionEditCommand(new TariffPlanServiceImpl(new DaoHelperFactory(),new TariffPlanValidator()));
            case PROMOTIONS_SAVE:
                return new PromotionSaveCommand(new PromotionServiceImpl(new DaoHelperFactory(), new PromotionValidator()), new TariffPlanServiceImpl(new DaoHelperFactory(), new TariffPlanValidator()));
            case PROMOTIONS:
                return new PromotionsCommand(new PromotionDtoServiceImpl(new DaoHelperFactory()), new PageController());

            case TARIFF_PLANS_EDIT:
                return new TariffPlanEditCommand();
            case TARIFF_PLANS_SAVE:
                return new TariffPlanSaveCommand(new TariffPlanServiceImpl(new DaoHelperFactory(), new TariffPlanValidator()));
            case TARIFF_PLANS:
                return new TariffPlansCommand(new TariffPlanServiceImpl(new DaoHelperFactory(),new TariffPlanValidator()), new PageController());

            case USER_SAVE:
                return new UserSaveCommand(new UserValidator(), new UserServiceImpl(new DaoHelperFactory()));
            case USER_EDIT:
                return new UserEditCommand(new TariffPlanServiceImpl(new DaoHelperFactory(), new TariffPlanValidator()));
            case USER_INFO:
                return new InfoPageCommand(new UserServiceImpl(new DaoHelperFactory()),
                        new TariffPlanServiceImpl(new DaoHelperFactory(), new TariffPlanValidator()),
                        new PromotionServiceImpl(new DaoHelperFactory(), new PromotionValidator()));
            case USER_PASSWORD_EDIT:
                return new UserEditPasswordCommand();
            case USER_PASSWORD_SAVE:
                return new UserSavePasswordCommand(new UserServiceImpl(new DaoHelperFactory()), new UserValidator());

            case USERS:
                return new UsersCommand(new UserDtoServiceImpl(new DaoHelperFactory()),
                        new UserServiceImpl(new DaoHelperFactory()), new PageController());
            case MAIN:
                return new GoToPage(MAIN_LOCATION);
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case LOGOUT:
                return new LogoutCommand();
            default:
                throw new IllegalArgumentException();
        }
    }
}

