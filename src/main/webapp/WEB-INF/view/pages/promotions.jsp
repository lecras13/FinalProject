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
    <c:choose>
        <c:when test="${sessionScope.lang eq 'en'}">
            <fmt:formatDate value="${promotion.startDate}" pattern="yyyy-MM-dd"/>
        </c:when>
        <c:otherwise>
            <fmt:formatDate value="${promotion.startDate}" pattern="dd.MM.yyyy"/>
        </c:otherwise>
    </c:choose>
    <a><ctg:info-time/></a>
    <hr/>
    <div class="info">
        <div class="table-users">
            <table class="users" width="60%" align="center" border="3">
                <tr>
                    <th><fmt:message key="number"/></th>
                    <th><fmt:message key="promotion.name"/></th>
                    <th><fmt:message key="promotion.start"/></th>
                    <th><fmt:message key="promotion.end"/></th>
                    <th><fmt:message key="promotion.description"/></th>
                    <th><fmt:message key="tariff.name"/></th>
                    <th><fmt:message key="promotion.new.price"/></th>
                    <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                        <th><fmt:message key="status"/></th>
                        <th><fmt:message key="button.edit"/></th>
                    </c:if>
                </tr>
                <c:forEach items="${promotions}" var="promotion" varStatus="index">
                    <tr>
                        <td>${(5)*(requestScope.currentPage - 1) + index.count}</td>
                        <td><c:out value="${promotion.promotionName}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.lang eq 'en'}">
                                    <fmt:formatDate value="${promotion.startDate}" pattern="yyyy-MM-dd"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatDate value="${promotion.startDate}" pattern="dd.MM.yyyy"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.lang eq 'en'}">
                                    <fmt:formatDate value="${promotion.endDate}" pattern="yyyy-MM-dd"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatDate value="${promotion.endDate}" pattern="dd.MM.yyyy"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${promotion.description}"/></td>
                        <td><c:out value="${promotion.tariffName}"/></td>
                        <td><c:out value="${promotion.newPrice}"/></td>
                        <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                            <td>
                                <c:if test="${promotion.status eq 'TRUE'}">
                                    <fmt:message key="status.block"/>
                                </c:if>
                                <c:if test="${promotion.status eq 'FALSE'}">
                                    <fmt:message key="status.unblock"/>
                                </c:if>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller?command=promotions-edit"
                                      method=POST>
                                    <input type="hidden" name="id" value="${promotion.id}">
                                    <input type="hidden" name="promotion_name" value="${promotion.promotionName}">
                                    <input type="hidden" name="start_date" value="${promotion.startDate}">
                                    <input type="hidden" name="end_date" value="${promotion.endDate}">
                                    <input type="hidden" name="description" value="${promotion.description}">
                                    <input type="hidden" name="status" value="${promotion.status}">
                                    <input type="hidden" name="tariff_id" value="<c:out value="${promotion.tariffId}"/>">
                                    <input type="hidden" name="new_price" value="${promotion.newPrice}">
                                    <input type="submit" value="<fmt:message key="button.edit"/>">
                                </form>
                               <%-- <form action="${pageContext.request.contextPath}/controller?command=promotions-delete"
                                      method=POST>
                                    <input type="hidden" name="id" value="${promotion.id}">
                                    <input type="hidden" name="tariff_id"
                                           value="<c:out value="${promotion.tariffPlan.id}"/>">
                                    <input type="submit" value="<fmt:message key="button.delete"/>">
                                </form>--%>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <div class="pagination" align="center">
                <c:if test="${currentPage != 1}">
                    <a href="${pageContext.request.contextPath}/controller?command=promotions&page=${currentPage - 1}">Previous</a>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=promotions&page=${i}">${i}</a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <a href="${pageContext.request.contextPath}/controller?command=promotions&page=${currentPage + 1}">Next</a>
                </c:if>
            </div>
            <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                <div align="center">
                    <form action="${pageContext.request.contextPath}/controller?command=promotions-edit" method=POST>
                        <input type="submit" value="<fmt:message key="button.add"/>">
                    </form>
                </div>
            </c:if>
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
