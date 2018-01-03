<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="property/admin/driver_all_info" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}" /> </title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#"><fmt:message key="label.home" bundle="${rb}" /></a></li>
        </ul>
        <div class="top-lang-list">
            <select id="mySelect" name="locale" onchange="location = this.options[this.selectedIndex].value;">
                <option disabled selected><fmt:message key="label.language" bundle="${rb}" /></option>
                <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">Русский</option>
                <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=be_BY">Мова</option>
                <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_US">English</option>
            </select>
        </div>
        <div class="top-button">
            <button class="button-small" id="aut-btn"><a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="label.LogOut" bundle="${rb}" /></a> </button>
        </div>
    </div>
</nav>
<section class="my-section">
    <div class="container">

        <table class="table">
            <h2><fmt:message key="label.driverInformation" bundle="${rb}" /> </h2>
            <th><fmt:message key="label.firstName" bundle="${rb}" /></th>
            <th><fmt:message key="label.secondName" bundle="${rb}" /></th>
            <th><fmt:message key="label.email" bundle="${rb}" /></th>
            <th><fmt:message key="label.birthDate" bundle="${rb}" /></th>
            <th><fmt:message key="label.ban" bundle="${rb}" /></th>
            <th><fmt:message key="label.phoneNumber" bundle="${rb}" /></th>
            <th><fmt:message key="label.rating" bundle="${rb}" /></th>
            <th><fmt:message key="label.tripAmount" bundle="${rb}" /></th>
            <th><fmt:message key="label.car" bundle="${rb}" /></th>
            <c:forEach items="${driverList}" var="driver">
                <tr>
                    <td>${driver.firstName}</td>
                    <td>${driver.secondName}</td>
                    <td>${driver.email}</td>
                    <td>${driver.birthDate}</td>
                    <c:if test="${empty driver.ban}">
                        <td><fmt:message key="label.emptyBan"  bundle="${rb}" /></td>
                    </c:if>
                    <c:if test="${not empty driver.ban}">
                        <td>${driver.ban.banDescription}</td>
                    </c:if>
                    <td>${driver.phoneNumber}</td>
                    <td>${driver.rating}</td>
                    <td>${driver.tripAmount}</td>
                    <c:if test="${not empty driver.car.mark}">
                        <td>${driver.car.mark}</td>
                    </c:if>
                    <c:if test="${empty driver.car.mark}">
                        <td><fmt:message key="label.emptyCar"  bundle="${rb}" /></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>

    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>
