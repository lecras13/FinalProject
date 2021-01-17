<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


<html lang="${sessionScope.lang}">
<head>
    <title>InternetProvider</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css">
</head>
<body>

<jsp:include page="../template/header.jsp"/>
<div id="content">
    <hr/>
    <a><ctg:info-time/></a>
    <hr/>

    <div class="info">
        <div class="payments">
            <table class="users" align="center" border="3">
                <tr>
                    <th><fmt:message key="number"/></th>
                    <th><fmt:message key="payments.sum"/></th>
                    <th><fmt:message key="payments.date"/></th>
                </tr>
                <ctg:payment-history/>
            </table>
            <div class="pagination" align="center">
                <c:if test="${currentPage != 1}">
                   <a href="${pageContext.request.contextPath}/controller?command=payments-history&page=${currentPage - 1}&user_id=${user_id}">Previous</a>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="${pageContext.request.contextPath}/controller?command=payments-history&page=${i}&user_id=${user_id}">${i}</a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <a href="${pageContext.request.contextPath}/controller?command=payments-history&page=${currentPage + 1}&user_id=${user_id}">Next</a>
                </c:if>
            </div>
        </div>
    </div>
</div>

<div class="infoF">
</div>
<footer>
    <jsp:include page="../template/footer.jsp"/>
</footer>

</body>
</html>
