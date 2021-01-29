package com.epam.internetprovider.tag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

/**
 * The {@code DateFormatLocale} represents class where is the date format defined.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class DateFormatLocale {
    private static final String LANG = "lang";
    private static final String EN = "en";
    private static final String DATE_FORMAT_EN = "yyyy-MM-dd";
    private static final String DATE_FORMAT_RU_BY = "dd.MM.yyyy";

    /**
     * Changing format date to choosing format
     *
     * @param servletRequest {@link HttpServletRequest} object the current servletRequest
     * @return current format date
     */
    public SimpleDateFormat transformationDateForLocale(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        String lang = String.valueOf(session.getAttribute(LANG));
        SimpleDateFormat simpleDateFormatRuBy = new SimpleDateFormat(DATE_FORMAT_RU_BY);
        SimpleDateFormat simpleDateFormatEn = new SimpleDateFormat(DATE_FORMAT_EN);
        if (lang.equals(EN)) {
            return simpleDateFormatEn;
        } else {
            return simpleDateFormatRuBy;
        }
    }
}
