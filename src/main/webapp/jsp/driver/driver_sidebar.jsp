<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/driver/driver_home" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
</head>
<body>
    <div class="nav-side-menu">
        <div class="brand">Brand Logo</div>
        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
        <div class="menu-list">
            <ul id="menu-content" class="menu-content collapse out">
                <li>
                    <a href="${pageContext.request.contextPath}/jsp/driver/driver_home.jsp"><i class="fa fa-globe fa-lg"></i> <fmt:message key="label.orders" bundle="${rb}" /></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/jsp/driver/driver_profile.jsp">
                        <i class="fa fa-user fa-lg"></i> <fmt:message key="label.profile" bundle="${rb}" />
                    </a>
                </li>
                <li>
                    <a href="#">
                        <a href="#"><i class="fa fa-car fa-lg"></i> <fmt:message key="label.vehicle" bundle="${rb}" /></a>
                    </a>
                </li>
                <li>
                    <a><i class="fa fa-gift fa-lg"></i> <fmt:message key="label.rewards" bundle="${rb}" /></a>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>
