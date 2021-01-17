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
<header>
    <jsp:include page="../template/header.jsp"/>
</header>
<div id="all-page">

    <div class="content">
        <hr/>
        <a><ctg:info-time/></a>
        <hr/>
        <h1 align="center"> ${errorMessageData}</h1>
        <br>

        <div class="leftMenu">
            <form style="display: inline-block"
                  action="${pageContext.request.contextPath}/controller?command=payments-history" method=POST>
                <input type="hidden" name="user_id" value="${user.id}"/>
                <input type="submit" value="<fmt:message key="button.history.payment"/>">
            </form>
            </br>
            <c:if test="${sessionScope.userRole eq 'USER'}">
                <form style="display: inline-block"
                      action="${pageContext.request.contextPath}/controller?command=payment" method=POST>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <input type="submit" value="<fmt:message key="button.add.payment"/>">
                </form>
                </br>
            </c:if>
            <form style="display: inline-block"
                  action="${pageContext.request.contextPath}/controller?command=user-edit"
                  method=POST>
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="hidden" name="login" value="${user.login}"/>
                <input type="hidden" name="first_name" value="<c:out value="${user.firstName}"/>"/>
                <input type="hidden" name="last_name" value="<c:out value="${user.lastName}"/>"/>
                <input type="hidden" name="role" value="<c:out value="${user.role}"/>"/>
                <input type="hidden" name="discount" value="<c:out value="${user.discount}"/>"/>
                <input type="hidden" name="status_block" value="${user.statusBlock}"/>
                <input type="hidden" name="tariff_id" value="<c:out value="${user.tariffId}"/>"/>
                <input type="hidden" name="traffic" value="<c:out value="${user.traffic}"/>"/>
                <input type="hidden" name="promotion_id" value="<c:out value="${user.promotionId}"/>"/>
                <input type="hidden" name="total_amount" value="<c:out value="${user.totalAmount}"/>"/>
                <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                    <input type="submit" value="<fmt:message key="button.edit.profile"/>">
                </c:if>
                <c:if test="${sessionScope.userRole eq 'USER'}">
                    <input type="submit" value="<fmt:message key="button.edit.tariff"/>">
                </c:if>
            </form>
            </br>
            <c:if test="${sessionScope.userRole eq 'USER'}">
                <form style="display: inline-block"
                      action="${pageContext.request.contextPath}/controller?command=user-password-edit"
                      method=POST>
                    <input type="submit" value="<fmt:message key="button.edit.password"/>">
                </form>
            </c:if>
        </div>

        <div class="info">
            <table class="users"  align="center" border="3">
                <tr>
                    <td><fmt:message key="login"/></td>
                    <td><c:out value="${user.login}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="first.name"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="last.name"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="total.amount"/></td>
                    <td><c:out value="${user.totalAmount}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="traffic"/></td>
                    <td><c:out value="${user.traffic}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="tariff"/></td>
                    <td><c:out value="${tariff_name}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="promotion.name"/></td>
                    <td><c:out value="${promotion_name}"/></td>
                </tr>
                <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                    <tr>
                        <td><fmt:message key="status"/></td>
                        <td>
                            <c:if test="${user.statusBlock eq 'TRUE'}">
                                <fmt:message key="status.block"/>
                            </c:if>
                            <c:if test="${user.statusBlock eq 'FALSE'}">
                                <fmt:message key="status.unblock"/>
                            </c:if>
                        </td>
                    </tr>
                </c:if>
            </table>
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
