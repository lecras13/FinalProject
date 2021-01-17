package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.command.PageController;
import com.epam.web.entity.Payment;
import com.epam.web.entity.Role;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int currentPage = pageController.getCurrentPage(request);

        Long id;
        HttpSession session = request.getSession();
        if (session.getAttribute(ROLE).equals(Role.USER)) {
            id = (Long) session.getAttribute(ID);
        } else {
            id = Long.parseLong(request.getParameter(ID));
        }

        List<Payment> paymentList = paymentService.getPaymentsForPage(id, (currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);

        int numberOfRecords = paymentService.getPayments(id).size();
        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);

        request.setAttribute(ID, id);
        request.setAttribute(NO_OF_PAGES, numberPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(PAYMENTS, paymentList);
        return CommandResult.forward(PAYMENTS_PAGE);
    }
}