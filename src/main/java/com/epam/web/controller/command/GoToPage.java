package com.epam.web.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code GoToPage} the class represents transition to page by creating {@code CommandResult} by StringPath.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class GoToPage implements Command {
    private final String page;

    public GoToPage(String page) {
        this.page = page;
    }

    /**
     *  Returns CommandResult object with further instructions to navigate to the page
     *
     * @param servletRequest  HttpServletRequest object the current servletRequest
     * @param servletResponse HttpServletResponse object the current servletResponse
     * @return CommandResult object navigate to the page
     */
    @Override
    public CommandResult execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return CommandResult.forward(page);
    }

}
