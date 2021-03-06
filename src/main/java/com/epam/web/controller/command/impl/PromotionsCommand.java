package com.epam.web.controller.command.impl;

import com.epam.web.controller.command.Command;
import com.epam.web.controller.command.CommandResult;
import com.epam.web.controller.command.PageController;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.PromotionDtoService;
import com.epam.web.service.PromotionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The {@code PromotionsCommand} the class represents transition to a page with promotions.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionsCommand implements Command {
    private static final String PROMOTIONS_PAGE = "/WEB-INF/view/pages/promotions.jsp";
    private static final String PROMOTIONS = "promotions";
    private static final int RECORDS_ON_PAGE = 5;
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private final PromotionDtoService promotionDtoService;
    private final PromotionService promotionService;
    private final PageController pageController;

    public PromotionsCommand(PromotionDtoService promotionDto, PromotionService promotionService, PageController pageController) {
        this.promotionDtoService = promotionDto;
        this.promotionService = promotionService;
        this.pageController = pageController;
    }

    /**
     * Execute command to show promotions using the parameters of HttpServletRequest object
     * and returns CommandResult to promotions page.
     *
     * @param servletRequest  {@link HttpServletRequest} object the current servletRequest
     * @param servletResponse {@link HttpServletResponse} object the current servletResponse
     * @return {@link CommandResult} object navigate to the page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServiceException {
        int currentPage = pageController.getCurrentPage(servletRequest);
        List<PromotionDto> promotionDtoList = promotionDtoService.getPromotionDtoForPage((currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);

        int numberOfRecords = promotionService.getPromotions().size();
        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);

        servletRequest.setAttribute(NO_OF_PAGES, numberPages);
        servletRequest.setAttribute(CURRENT_PAGE, currentPage);
        servletRequest.setAttribute(PROMOTIONS, promotionDtoList);
        return CommandResult.forward(PROMOTIONS_PAGE);
    }
}
