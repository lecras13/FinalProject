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
    <div id="content">
        <hr/>
        <a><ctg:info-time/></a>
        <hr/>
        <div class="infosecond">
            <fmt:message key="text.main1"/>
            <br>
            <br>
            <fmt:message key="text.main2"/>
            <br>
            <br>
            <fmt:message key="text.main3"/>
        </div>
        <div class="infosecond">
            <div class="title">
                <fmt:message key="title1"/>
            </div>
            <div class="text">
                <fmt:message key="text1"/>
            </div>
            <div class="title">
                <fmt:message key="title2"/>
            </div>
            <div class="text">
                <fmt:message key="text2"/>
            </div>
            <div class="title">
                <fmt:message key="title3"/>
            </div>
            <div class="text">
                <fmt:message key="text3"/>
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
