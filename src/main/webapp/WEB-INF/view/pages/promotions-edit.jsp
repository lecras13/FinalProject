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
        <form action="${pageContext.request.contextPath}/controller?command=promotions-save" method=POST>
            <input type="hidden" name="id" value="${id}"/>
            <table class="reg_form">
                <tr>
                    <td><fmt:message key="promotion.name"/></td>
                    <td><input type="text" id="promotionName" name="promotion-name" value="${promotion_name}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="promotion.start"/></td>
                    <td><input type="date" id="startDate" name="start-date" value="${start_date}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="promotion.end"/></td>
                    <td><input type="date" id="endDate" name="end-date" value="${end_date}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="promotion.description"/></td>
                    <td><textarea id="description" name="description">${description}</textarea></td>
                </tr>
                <tr>
                    <td><fmt:message key="tariff.name"/></td>
                    <td>
                        <select name="select_tariff">
                            <c:forEach items="${tariffs}" var="tariff">
                                <c:if test="${tariff.id eq tariff_id}">
                                    <option value="${tariff.id}" selected> ${tariff.tariffName} </option>
                                </c:if>
                                <c:if test="${!(tariff.id eq tariff_id)}">
                                    <option value="${tariff.id}"> ${tariff.tariffName} </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="promotion.new.price"/></td>
                    <td><input type="text" id="newPrice" name="new-price" value="${new_price}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="status"/></td>
                    <td>
                        <c:if test="${status eq true}">
                            <input type="radio" name="status" value="TRUE" checked><fmt:message key="status.blocked"/>
                            <input type="radio" name="status" value="FALSE"><fmt:message
                                key="status.unblocked"/><br>
                        </c:if>
                        <c:if test="${!status eq true}">
                            <input type="radio" name="status" value="TRUE"><fmt:message key="status.blocked"/>
                            <input type="radio" name="status" value="FALSE" checked><fmt:message
                                key="status.unblocked"/><br>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <h1 align="center"> ${errorMessageData}</h1>
                    </td>
                    <td>
                        <input id="button-submit" type="submit" value="<fmt:message key="save"/>">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="infoF">

</div>

<footer>
    <jsp:include page="../template/footer.jsp"/>
</footer>
<script src="<c:url value="/static/js/promotion.js"/>"></script>
</body>
</html>
