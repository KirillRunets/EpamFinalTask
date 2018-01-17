<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/authentication/login/logIn" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
</head>
<body>
<div class="top-lang-list">
    <form id="changeLocaleForm" action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="uri" value=${pageContext.request.requestURI}>
        <select id="mySelect" name="locale" onchange="submitForm('changeLocaleForm')">
            <option disabled selected><fmt:message key="label.language" bundle="${rb}" /></option>
            <option value="ru_RU">Русский</option>
            <option value="be_BY">Мова</option>
            <option value="en_US">English</option>
        </select>
    </form>
</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
