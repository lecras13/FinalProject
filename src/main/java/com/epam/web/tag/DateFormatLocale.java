package com.epam.web.tag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

public class DateFormatLocale {
    private static final String LANG = "lang";
    private static final String EN = "en";
    private static final String DATE_FORMAT_EN = "yyyy-MM-dd";
    private static final String DATE_FORMAT_RU_BY = "dd.MM.yyyy";

    SimpleDateFormat transformationDateForLocale(ServletRequest servletRequest) {
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
