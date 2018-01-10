<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/driver/driver_home" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}"/></title>
    <link href="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css" />
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sb-admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
</head>
<body class="custom-body">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="">Buber</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#"><fmt:message key="label.orders" bundle="${rb}" /></a></li>
            </ul>
            <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
            <div class="top-button">
                <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message key="label.LogOut" bundle="${rb}"/></button>
            </div>
        </div>
    </nav>
    <div class="nav-side-menu">
        <div class="brand">Brand Logo</div>
        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>

        <div class="menu-list">

            <ul id="menu-content" class="menu-content collapse out">
                <li class="active">
                    <a href="#"><i class="fa fa-globe fa-lg"></i><fmt:message key="label.orders" bundle="${rb}" /></a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-user fa-lg"></i> <fmt:message key="label.profile" bundle="${rb}" />

                    </a>
                </li>
                <li>
                    <a href="#">
                        <a href="#"><i class="fa fa-car fa-lg"></i><fmt:message key="label.vehicle" bundle="${rb}" /></a>
                    </a>
                </li>

                <li>
                    <a><i class="fa fa-gift fa-lg"></i><fmt:message key="label.rewards" bundle="${rb}" /></a>
                </li>
            </ul>
        </div>
    </div>
    <section class="my-section">
        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Data Table Example</div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.date" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.tripCost" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.distance" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.departurePoint" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.destinationPoint" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerFirstName" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerSecondName" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerPhoneNumber" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerRating" bundle="${rb}"/> </th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th><fmt:message key="label.date" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.tripCost" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.distance" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.departurePoint" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.destinationPoint" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerFirstName" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerSecondName" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerPhoneNumber" bundle="${rb}"/> </th>
                                    <th><fmt:message key="label.passengerRating" bundle="${rb}"/> </th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${orderList}" var="order">
                                    <tr class="line" id="${order.id}">
                                        <td>${order.date}</td>
                                        <td>${order.cost}</td>
                                        <td>${order.email}</td>
                                        <td>${order.birthDate}</td>
                                        <td>${user.phoneNumber}</td>
                                        <td>${user.rating}</td>
                                        <td>${user.tripAmount}</td>
                                        <td>${user.role.roleName}</td>
                                        <c:if test="${not empty user.car.mark}">
                                            <td>${user.car.mark}</td>
                                        </c:if>
                                        <td>${user.ban.banType}</td>
                                        <td>${user.ban.banDescription}</td>
                                        <td>${user.unBaneDate}</td>
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
    <script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
