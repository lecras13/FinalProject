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

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int currentPage = pageController.getCurrentPage(request);
        List<PromotionDto> promotionDtoList = promotionDtoService.getPromotionDtoForPage((currentPage - 1) * RECORDS_ON_PAGE, RECORDS_ON_PAGE);

        int numberOfRecords = promotionService.getPromotions().size();
        int numberPages = pageController.getNumberPages(numberOfRecords, RECORDS_ON_PAGE);

        request.setAttribute(NO_OF_PAGES, numberPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(PROMOTIONS, promotionDtoList);
        return CommandResult.forward(PROMOTIONS_PAGE);
    }
}
