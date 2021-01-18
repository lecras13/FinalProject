package com.epam.web.tag;

import com.epam.web.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The {@code PaymentHistoryTag} represents class a custom tag that displays the payments history.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PaymentHistoryTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(PaymentHistoryTag.class);
    private static final String PAYMENTS = "payments";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String TAG_ROW_OPEN = "<tr>";
    private static final String TAG_ROW_CLOSE = "</tr>";
    private static final String TAG_COLUMN_OPEN = "<td>";
    private static final String TAG_COLUMN_CLOSE = "</td>";
    private final DateFormatLocale dateFormat = new DateFormatLocale();

    /**
     * Displays payments history
     *
     * @return tells the container that it should ignore everything between the start and end tag.
     */
    @Override
    public int doStartTag() {
        ServletRequest servletRequest = pageContext.getRequest();
        SimpleDateFormat simpleDateFormat = dateFormat.transformationDateForLocale(servletRequest);
        List<Payment> paymentList = (List<Payment>) servletRequest.getAttribute(PAYMENTS);
        int count = ((int) servletRequest.getAttribute(CURRENT_PAGE) - 1) * 10;
        int index = 0;
        while (index < paymentList.size()) {
            Payment payment = paymentList.get(index);
            index++;
            try {
                pageContext.getOut().write(TAG_ROW_OPEN + TAG_COLUMN_OPEN + (index + count) + TAG_COLUMN_CLOSE + TAG_COLUMN_OPEN + simpleDateFormat.format(payment.getPaymentDate()) + TAG_COLUMN_CLOSE +
                        TAG_COLUMN_OPEN + payment.getAmount() + TAG_COLUMN_CLOSE + TAG_ROW_CLOSE);
            } catch (IOException e) {
                LOGGER.error("Error while write out");
            }
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

