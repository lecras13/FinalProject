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

        <form action="${pageContext.request.contextPath}/controller?command=tariffs-save" method=POST>
            <input type="hidden" name="id" value="${tariff_id}"/>
            <table class="reg_form">
                <tr>
                    <td><fmt:message key="tariff.name"/></td>
                    <td><input type="text" id="tariff" name="tariff" value="${tariff}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="tariff.price"/></td>
                    <td><input type="text" id="price" name="price" value="${price}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="tariff.description"/></td>
                    <td><textarea id="description" name="description">${description}</textarea>
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
                    <td><p> ${errorMessageData}</p></td>
                    <td></td>
                </tr>
                <td>
                </td>
                <td>
                    <input id="button-submit" type="submit" value="<fmt:message key="save"/>">
                </td>
            </table>
        </form>
    </div>
</div>
<footer>
    <jsp:include page="../template/footer.jsp"/>
</footer>
<script src="<c:url value="/static/js/tariff.js"/>"></script>
</body>
</html>
