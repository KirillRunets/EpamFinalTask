<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/passenger/passenger_home" var="rb"/>
<%@ taglib prefix="number" uri="customtag" %>

<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}"/></title>
    <link href="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css"/>
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sb-admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/passenger.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#"><fmt:message key="label.orders" bundle="${rb}"/></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.logout" bundle="${rb}"/></button>
        </div>
        <div class="name">
            <ul class="nav navbar-nav">
                <li><a href="#"><span
                        class="glyphicon glyphicon-user"></span>${sessionScope.USER.firstName} ${sessionScope.USER.secondName}
                </a></li>
            </ul>
        </div>
    </div>
</nav>
<c:import url="${pageContext.request.contextPath}/jsp/passenger/passenger_sidebar.jsp"/>
<section class="my-section">
    <div class="content-wrapper">
        <div class="container-fluid">
            <div class="card mb-3">
                <div class="card-body">
                    <div class="table-responsive">
                        <h1><fmt:message key="label.orders" bundle="${rb}"/></h1>
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th><fmt:message key="label.date" bundle="${rb}"/></th>
                                <th><fmt:message key="label.tripCost" bundle="${rb}"/></th>
                                <th><fmt:message key="label.distance" bundle="${rb}"/></th>
                                <th><fmt:message key="label.departurePoint" bundle="${rb}"/></th>
                                <th><fmt:message key="label.destinationPoint" bundle="${rb}"/></th>
                                <th><fmt:message key="label.driverFirstName" bundle="${rb}"/></th>
                                <th><fmt:message key="label.driverSecondName" bundle="${rb}"/></th>
                                <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th><fmt:message key="label.date" bundle="${rb}"/></th>
                                <th><fmt:message key="label.tripCost" bundle="${rb}"/></th>
                                <th><fmt:message key="label.distance" bundle="${rb}"/></th>
                                <th><fmt:message key="label.departurePoint" bundle="${rb}"/></th>
                                <th><fmt:message key="label.destinationPoint" bundle="${rb}"/></th>
                                <th><fmt:message key="label.driverFirstName" bundle="${rb}"/></th>
                                <th><fmt:message key="label.driverSecondName" bundle="${rb}"/></th>
                                <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${sessionScope.USER.orderSet}" var="order">
                                <tr id="${order.id}">
                                    <td><fmt:formatDate value="${order.orderDate}"/></td>
                                    <td><number:numberFormatterTag format="##.00" number="${order.tripCost}"/>
                                        <fmt:message key="label.systemСost" bundle="${rb}"/><fmt:formatNumber var="cost"
                                                                                                              value=""/></td>
                                    <td><number:numberFormatterTag format="##.00" number="${order.distance}"/>
                                        <fmt:message key="label.systemDistance" bundle="${rb}"/></td>
                                    <td>${order.startPoint}</td>
                                    <td>${order.destinationPoint}</td>
                                    <td>${order.driver.firstName}</td>
                                    <td>${order.driver.secondName}</td>
                                    <td>${order.driver.phoneNumber}</td>
                                    <td>
                                        <div class="static-rating">
                                            <span class="fa fa-star-o" data-rating="1"></span>
                                            <span class="fa fa-star-o" data-rating="2"></span>
                                            <span class="fa fa-star-o" data-rating="3"></span>
                                            <span class="fa fa-star-o" data-rating="4"></span>
                                            <span class="fa fa-star-o" data-rating="5"></span>
                                            <input type="hidden" name="whatever1" class="rating-value"
                                                   value="${order.driver.rating}">
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin-datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/chart/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/chart.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
<script src="${pageContext.request.contextPath}/js/modal.js"></script>
<script src="${pageContext.request.contextPath}/js/rating.js"></script>
</body>
</html>
