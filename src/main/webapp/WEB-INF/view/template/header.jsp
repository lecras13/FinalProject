<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<body>
<header>
    <div class="header-panel">
        <div class="header-line">
            <div class="site-name">
                <h1><fmt:message key="label.nameCompany"/></h1>
            </div>

            <div class="tool-bar">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/controller?command=main"><fmt:message
                            key="toolbar.main"/></a></li>
                    <c:if test="${sessionScope.userRole eq 'USER'}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=info"><fmt:message
                                key="toolbar.info"/></a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=users"
                               method=POST><fmt:message
                                key="user.list"/></a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/controller?command=tariffs" method=POST><fmt:message
                            key="toolbar.tariff"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=promotions"
                           method=POST><fmt:message
                            key="toolbar.promotions"/></a></li>

                    <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=tariffs-edit"
                               method=POST><fmt:message
                                key="tariff.add"/></a></li>

                        <li><a href="${pageContext.request.contextPath}/controller?command=promotions-edit" method=POST><fmt:message
                                key="promotion.add"/></a></li>
                    </c:if>
                </ul>
            </div>

            <div class="language">
                <li>
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="en"/>
                        <button class="button-lang" type="submit"><fmt:message key="label.lang.en"/></button>
                    </form>
                </li>
                <li>
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="ru"/>
                        <button class="button-lang" type="submit"><fmt:message key="label.lang.ru"/></button>
                    </form>
                </li>
                <li>
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="by"/>
                        <button class="button-lang" type="submit"><fmt:message key="label.lang.by"/></button>
                    </form>
                </li>

            </div>
        </div>

        <c:if test="${(sessionScope.userRole eq null)}">
            <div class="login">
                <form action="${pageContext.request.contextPath}/controller?command=login" method=POST>
                    <br>
                    <input type="text" name="login" placeholder="<fmt:message key="login.form.log" />">
                    <br>
                    <input type="password" name="password" placeholder="<fmt:message key="login.form.pass" />">
                    <br>
                    <input type="submit" value="<fmt:message key="login.form.button" />">
                </form>
                <p class="errorLogin"> ${errorMessage}</p>
            </div>
        </c:if>
        <c:if test="${!(sessionScope.userRole eq null)}">
            <div class="right">
                <H2><fmt:message key="hello"/>, ${sessionScope.login}!</H2>
                <h4><a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message
                        key="logout.form.button"/></a></h4>
            </div>
        </c:if>
    </div>
</header>
</body>
</html>
