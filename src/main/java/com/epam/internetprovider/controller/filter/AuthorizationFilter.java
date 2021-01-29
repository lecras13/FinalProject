package com.epam.internetprovider.controller.filter;

import com.epam.internetprovider.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AuthorizationFilter} a class that implements the {@code Filter} interface, defining access by role
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class AuthorizationFilter implements Filter {
    private static final String COMMAND = "command";
    private static final String ROLE = "userRole";
    private static final int ERROR_403 = 403;
    private  List<String> adminCommandList;;
    private  List<String> userCommandList;
    private  List<String> guestCommandList;

    /**
     * Initialization of access commands
     *
     * @param filterConfig FilterConfig object the current filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        adminCommandList = new ArrayList<>();
        adminCommandList.add("users");
        adminCommandList.add("main");
        adminCommandList.add("logout");
        adminCommandList.add("tariffs-save");
        adminCommandList.add("tariffs-edit");
        adminCommandList.add("promotions-save");
        adminCommandList.add("promotions-edit");
        adminCommandList.add("info");
        adminCommandList.add("payments-history");
        adminCommandList.add("user-edit");
        adminCommandList.add("user-save");
        userCommandList = new ArrayList<>();
        userCommandList.add("main");
        userCommandList.add("logout");
        userCommandList.add("info");
        userCommandList.add("payments-history");
        userCommandList.add("payment-pay");
        userCommandList.add("payment");
        userCommandList.add("user-edit");
        userCommandList.add("user-save");
        userCommandList.add("user-password-edit");
        userCommandList.add("user-password-save");
        guestCommandList = new ArrayList<>();
        guestCommandList.add("main");
        guestCommandList.add("login");
        guestCommandList.add("registration");
        guestCommandList.add("tariffs");
        guestCommandList.add("promotions");
    }

    /**
     * Performs filter work directly
     *
     * @param servletRequest HttpServletRequest object the current servletRequest
     * @param servletResponse HttpServletResponse object the current servletResponse
     * @param filterChain FilterChain object the current filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute(ROLE);
        String command = request.getParameter(COMMAND);

        if (command != null && !guestCommandList.contains(command)) {
            if (role.equals(Role.ADMIN) && adminCommandList.contains(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (role.equals(Role.USER) && userCommandList.contains(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendError(ERROR_403);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
