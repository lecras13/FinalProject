package com.epam.web.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code InfoDateTag} represents class a custom tag that displays the date.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class InfoDateTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(InfoDateTag.class);
    private final DateFormatLocale dateFormat = new DateFormatLocale();

    /**
     * Displays date
     *
     * @return tells the container that it should ignore everything between the start and end tag.
     */
    @Override
    public int doStartTag() throws JspException {
        ServletRequest servletRequest = pageContext.getRequest();
        SimpleDateFormat simpleDateFormat = dateFormat.transformationDateForLocale(servletRequest);
        Date date = new Date();
        try {
            JspWriter out = pageContext.getOut();
            out.write(simpleDateFormat.format(date));
        } catch (IOException e) {
            LOGGER.error("IOException in infoTimeTag!");
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    /**
     * Ending work tag
     *
     * @return tells container that needs to continue processing the JSP page
     */
    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
