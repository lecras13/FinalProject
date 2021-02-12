package com.epam.internetprovider.controller.command.impl;

import com.epam.internetprovider.controller.command.Command;
import com.epam.internetprovider.controller.command.CommandResult;
import com.epam.internetprovider.controller.command.PageController;
import com.epam.internetprovider.entity.Payment;
import com.epam.internetprovider.entity.Role;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code PaymentHistoryCommand} the class represents transition history payments current user.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PaymentHistoryCommand implements Command {
    private static final String PAYMENTS_PAGE = "/WEB-INF/view/pages/payments.jsp";
    private static final String PAYMENTS = "payments";
    private static final String ID = "user_id";
    private static final String ROLE = "userRole";
    private static final int RECORDS_ON_PAGE = 10;
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private final PaymentService paymentService;
    private final PageController pageController;

    public PaymentHistoryCommand(PaymentService paymentService, PageController pageController) {
        this.paymentService = paymentService;
        this.pageController = pageController;
    }

    /**
     * Execute command to payment history using the parameters of HttpServletRequest object
     * and returns CommandResult to history payments page current user.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        int currentPage = pageController.getCurrentPage(servletRequest);
        servletRequest.setAttribute(CURRENT_PAGE, currentPage);

        Long id;
        HttpSession session = servletRequest.getSession();
        if (session.getAttribute(ROLE).equals(Role.USER)) {
            id = (Long) session.getAttribute(ID);
        } else {
            id = Long.parseLong(servletRequest.getParameter(ID));
        }
        servletRequest.setAttribute(ID, id);

        List<Payment> paymentList = paymentService.getPaymentsForPage(id, (currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);
        servletRequest.setAttribute(PAYMENTS, paymentList);

        int numberOfRecords = paymentService.getPayments(id).size();
        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);
        servletRequest.setAttribute(NO_OF_PAGES, numberPages);

        return CommandResult.forward(PAYMENTS_PAGE);
    }
}