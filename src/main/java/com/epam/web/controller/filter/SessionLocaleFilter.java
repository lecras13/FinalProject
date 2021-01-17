package com.epam.web.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The {@code SessionLocaleFilter} a class that implements the {@code Filter} interface, setting language
 *
 * @author Roman Alexandrov
 * @version 1.0
 */
public class SessionLocaleFilter implements Filter {
    private static final String LANG_PARAMETER = "lang";

    /**
     * Performs filter work directly
     *
     * @param servletRequest HttpServletRequest object the current servletRequest
     * @param servletResponse HttpServletResponse object the current servletResponse
     * @param filterChain FilterChain object the current filterChain
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String sessionLocale = req.getParameter(LANG_PARAMETER);
        if (sessionLocale != null) {
            req.getSession().setAttribute(LANG_PARAMETER, sessionLocale);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }

    public void init(FilterConfig arg) {
    }
}