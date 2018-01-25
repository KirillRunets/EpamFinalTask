<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="number" uri="customtag" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/passenger/passenger_home" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css"/>
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sb-admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/passenger.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/loader.css">
</head>
<body class="custom-body">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#"><fmt:message key="label.drivers" bundle="${rb}"/></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message key="label.logout" bundle="${rb}"/></button>
        </div>
        <div class="name">
            <ul class="nav navbar-nav">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>${sessionScope.USER.firstName} ${sessionScope.USER.secondName}</a></li>
            </ul>
        </div>
    </div>
</nav>
<c:out value="${param.command}"/>
<c:import url="${pageContext.request.contextPath}/jsp/passenger/passenger_sidebar.jsp"/>
<section class="my-section">
    <div class="content-wrapper">
        <div class="container-fluid">
            <div class="statistics">
                <table id="stat-table">
                    <tr>
                        <th><fmt:message key="label.distance" bundle="${rb}"/></th>
                        <th><fmt:message key="label.time" bundle="${rb}"/></th>
                        <th><fmt:message key="label.tripCost" bundle="${rb}"/></th>
                        <th><fmt:message key="label.averageSpeed" bundle="${rb}"/></th>
                        <th><fmt:message key="label.currentLocation" bundle="${rb}"/></th>
                    </tr>
                    <fmt:formatNumber var="distance" value="${sessionScope.tripDistance}" maxFractionDigits="0"/>
                    <fmt:formatNumber var="time" value="${sessionScope.tripTime}" maxFractionDigits="0"/>
                    <fmt:formatNumber var="cost" value="${sessionScope.tripCost}" maxFractionDigits="0"/>
                    <fmt:formatNumber var="averageSpeed" value="${sessionScope.averageSpeed}" maxFractionDigits="0"/>
                    <tr>
                        <td>
                            <p><number:numberFormatterTag format="##.00" number="${distance}"/> <fmt:message key="label.systemDistance" bundle="${rb}"/> </p>
                        </td>
                        <td>
                            <p>${time} <fmt:message key="label.systemTime" bundle="${rb}"/></p>
                        </td>
                        <td>

                            <p><number:numberFormatterTag format="###.00" number="${cost}"/> <fmt:message key="label.systemСost" bundle="${rb}"/></p>
                        </td>
                        <td>
                            <p>${averageSpeed} <fmt:message key="label.systemSpeed" bundle="${rb}"/></p>
                        </td>
                        <td>
                            <p>${sessionScope.USER.currentLocation}</p>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="card mb-3">
                <div class="card-body">
                    <div class="table-responsive">
                        <form name="carListForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" id="command" value="make_order">
                            <input type="hidden" name="driver_id" id="driver_id" value="">
                            <table class="table table-bordered" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.currentLocation" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.mark" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.model" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.release_date" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.licensePlate" bundle="${rb}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sessionScope.priorityDriversQueue}" var="driver">
                                    <tr class="line" id="${driver.id}">
                                        <td>${driver.firstName}</td>
                                        <td>${driver.secondName}</td>
                                        <td><fmt:formatDate value="${driver.birthDate}" /></td>
                                        <td>${driver.phoneNumber}</td>
                                        <td>${driver.rating}</td>
                                        <td>${driver.tripAmount}</td>
                                        <td>${driver.currentLocation}</td>
                                        <c:if test="${not empty driver.car.mark}">
                                            <td>${driver.car.mark}</td>
                                            <td>${driver.car.model}</td>
                                            <td><fmt:formatDate value="${driver.car.releaseDate}" /></td>
                                            <td>${driver.car.licensePlate}</td>
                                        </c:if>
                                        <c:if test="${empty driver.car.mark}">
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                            <td>-</td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="button-container">
                                <button id="btn-load1" class="button-small" type="submit" onclick="loadCommand('order')"><fmt:message key="label.makeOrder" bundle="${rb}"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<c:if test="${sessionScope.newOrder.confirmed == 'false'}">
    <div class="static-modal">
        <div class="modal-content">
            <div class="loader"></div>
            <p><fmt:message key="label.wait" bundle="${rb}" /></p>
        </div>
    </div>
</c:if>
<c:if test="${sessionScope.newOrder.confirmed == 'true'}">
    <div class="static-modal">
        <div class="modal-content">
            <p><fmt:message key="label.confirmed" bundle="${rb}" /></p>
        </div>
    </div>
</c:if>
<%--<c:if test="${not empty param.errorLabel}">
    <div class="static-modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <p>${param.errorLabel}</p>
        </div>
    </div>
</c:if>
<c:if test="${requestScope.command == 'driver_confirm_order'}">
    <div class="static-modal">
        <div class="modal-content">
            <div class="loader"></div>
            <p><fmt:message key="label.wait" bundle="${rb}" /></p>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/controller" method="POST" id="driver_confirm">
        <input type="hidden" name="command" value="${param.command}">
    </form>
</c:if>--%>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin-datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
<script src="${pageContext.request.contextPath}/js/websocket.js"></script>
</body>
</html>
