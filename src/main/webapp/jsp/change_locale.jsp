<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/authentication/login/logIn" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/form_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
</head>
<body>
    <div class="top-lang-list">
        <select id="mySelect" name="locale" onchange="location = this.options[this.selectedIndex].value;">
            <option disabled selected><fmt:message key="label.language" bundle="${rb}" /></option>
            <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU&uri=${pageContext.request.requestURI}">Русский</option>
            <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=be_BY&uri=${pageContext.request.requestURI}">Мова</option>
            <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_US&uri=${pageContext.request.requestURI}">English</option>
        </select>
    </div>
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
</body>
</html>
