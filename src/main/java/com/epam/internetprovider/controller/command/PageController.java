package com.epam.internetprovider.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code PageController} the class represents work with pagination.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PageController {
    private static final String PAGE = "page";

    /**
     * Calculates current page
     *
     * @return the current page
     */
    public int getCurrentPage(HttpServletRequest request){
        int page = 1;
        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }
        return page;
    }

    /**
     * Calculates number of pages
     *
     * @return the number of pages
     */
    public int getNumberPages(int numberRecords, int numberRecordsForPage){
        return  (int) Math.ceil(numberRecords * 1.0 / numberRecordsForPage);
    }
}
