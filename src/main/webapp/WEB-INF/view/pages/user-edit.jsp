<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>


<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
        <form action="${pageContext.request.contextPath}/controller?command=user-save" method=POST>
            <input type="hidden" name="id" value="${id}"/>
            <table class="reg_form">
                <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                    <tr>
                        <td><fmt:message key="login"/></td>
                        <td><input type="text" id="login" name="login" value="<c:out value="${login}"/>"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="first.name"/></td>
                        <td><input type="text" id="firstName" name="first_name" value="<c:out value="${first_name}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="last.name"/></td>
                        <td><input type="text" id="lastName" name="last_name" value="<c:out value="${last_name}"/>"/>
                        </td>
                    </tr>
                </c:if>
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

                <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                    <tr>
                        <td><fmt:message key="tariff.discount"/></td>
                        <td>
                            <input type="text" id="discount" name="discount" value="<c:out value="${discount}"/>"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="status"/></td>
                        <td>
                            <input type="radio" name="status_block" value="TRUE"><fmt:message key="status.blocked"/>
                            <input type="radio" name="status_block" value="FALSE" checked><fmt:message
                                key="status.unblocked"/><br>
                        </td>
                    </tr>
                </c:if>

                <tr>
                    <td>
                    </td>
                    <td>
                        <input type="hidden" name="login" value="<c:out value="${login}"/>"/>
                        <input type="hidden" name="first_name" value="<c:out value="${first_name}"/>"/>
                        <input type="hidden" name="last_name" value="<c:out value="${last_name}"/>"/>
                        <input type="hidden" name="discount" value="<c:out value="${discount}"/>"/>
                        <input type="hidden" name="tariff_id" value="<c:out value="${tariff_id}"/>"/>
                        <input id="button-submit" type="submit" value="<fmt:message key="save"/>"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<footer>
    <jsp:include page="../template/footer.jsp"/>
</footer>
<script src="<c:url value="/static/js/user.js"/>"></script>
</body>
</html>
