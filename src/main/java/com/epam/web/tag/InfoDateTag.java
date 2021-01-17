package com.epam.web.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class InfoDateTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(InfoDateTag.class);
    private final DateFormatLocale dateFormat = new DateFormatLocale();

    @Override
    public int doStartTag() throws JspException {
        ServletRequest servletRequest = pageContext.getRequest();
        SimpleDateFormat simpleDateFormat = dateFormat.transformationDateForLocale(servletRequest);
        Date date =new Date();
        try {
            JspWriter out = pageContext.getOut();
            out.write(simpleDateFormat.format(date));
        } catch (IOException e) {
            LOGGER.error("IOException in infoTimeTag!");
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
