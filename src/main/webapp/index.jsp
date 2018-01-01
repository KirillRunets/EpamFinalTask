<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="property/authentication/login/logIn" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body class="main-body">
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.user.ADMIN}" >
                <jsp:forward page="jsp/admin/admin_home.jsp"/>
            </c:if>
            <c:if test="${sessionScope.user.DRIVER}">
                <jsp:forward page="jsp/driver/driver_home.jsp"/>
            </c:if>
            <c:if test="${sessionScope.user.PASSENGER}">
                <jsp:forward page="jsp/passenger/passenger_home.jsp"/>
            </c:if>
        </c:when>
        <c:otherwise>
            <jsp:forward page="jsp/main.jsp"/>
        </c:otherwise>
    </c:choose>
</body>
</html>
