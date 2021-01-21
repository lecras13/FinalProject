<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
        <div class="table-users">
            <table class="users" width="60%" align="center" border="3">
                <tr>
                    <th><fmt:message key="number"/></th>
                    <th><fmt:message key="tariff.name"/></th>
                    <th><fmt:message key="tariff.price"/></th>
                    <th><fmt:message key="tariff.description"/></th>
                    <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                        <th><fmt:message key="status"/></th>
                        <th><fmt:message key="button.edit"/></th>
                    </c:if>
                </tr>

                <c:forEach items="${tariffs}" var="tariff" varStatus="index">
                    <tr>
                        <td>${(5)*(requestScope.currentPage - 1) + index.count}</td>
                        <td><c:out value="${tariff.tariffName}"/></td>
                        <td><c:out value="${tariff.price}"/></td>
                        <td><c:out value="${tariff.description}"/></td>

                        <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                            <td>
                                <c:if test="${tariff.block eq 'TRUE'}">
                                    <fmt:message key="status.block"/>
                                </c:if>
                                <c:if test="${tariff.block eq 'FALSE'}">
                                    <fmt:message key="status.unblock"/>
                                </c:if>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller?command=tariffs-edit"
                                      method="POST">
                                    <input type="hidden" name="tariff_id" value="<c:out value="${tariff.id}"/>"/>
                                    <input type="hidden" name="tariff" value="${tariff.tariffName}">
                                    <input type="hidden" name="price" value="${tariff.price}">
                                    <input type="hidden" name="description" value="${tariff.description}">
                                    <input type="hidden" name="status" value="${tariff.block}">
                                    <input type="submit" value="<fmt:message key="button.edit"/>">
                                </form>
                               <%-- <form action="${pageContext.request.contextPath}/controller?command=tariffs-delete"
                                      method=POST>
                                    <input type="hidden" name="tariff_id" value="<c:out value="${tariff.id}"/>"/>
                                    <input type="submit" value="<fmt:message key="button.delete"/>">
                                </form>--%>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <div class="pagination" align="center">
                <c:if test="${currentPage != 1}">
                    <a href="${pageContext.request.contextPath}/controller?command=tariffs&page=${currentPage - 1}">Previous</a>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="${pageContext.request.contextPath}/controller?command=tariffs&page=${i}">${i}</a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <a href="${pageContext.request.contextPath}/controller?command=tariffs&page=${currentPage + 1}">Next</a>
                </c:if>
            </div>
            <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                <div  align="center">
                    <form action="${pageContext.request.contextPath}/controller?command=tariffs-edit" method=POST>
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
