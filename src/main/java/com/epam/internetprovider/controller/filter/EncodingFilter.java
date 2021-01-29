package com.epam.internetprovider.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * The {@code EncodingFilter} a class that implements the {@code Filter} interface, forming a request in a given encoding
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class EncodingFilter implements Filter {
    private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String encoding;

    public void destroy() {
    }

    /**
     * Performs filter work directly
     *
     * @param servletRequest HttpServletRequest object the current servletRequest
     * @param servletResponse HttpServletResponse object the current servletResponse
     * @param filterChain FilterChain object the current filterChain
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        String contentType = servletRequest.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
            servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Initialization encoding type
     *
     * @param filterConfig FilterConfig object the current filterConfig
     */
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }
}