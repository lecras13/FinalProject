package com.epam.web.controller.command;

import javax.servlet.http.HttpServletRequest;

public class PageController {
    private static final String PAGE = "page";

    public int getCurrentPage(HttpServletRequest request){
        int page = 1;
        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }
        return page;
    }

    public int getNumberPages(int numberRecords, int numberRecordsForPage){
        return  (int) Math.ceil(numberRecords * 1.0 / numberRecordsForPage);
    }
}
