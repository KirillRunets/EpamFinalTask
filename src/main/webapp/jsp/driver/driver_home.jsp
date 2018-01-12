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
                <a class="navbar-brand" href="#">Buber</a>
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
    <c:import url="${pageContext.request.contextPath}/jsp/driver/driver_sidebar.jsp"/>
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
                                    <c:forEach items="${sessionScope.USER.orderSet}" var="order">
                                        <tr class="line" id="${order.id}">
                                            <td>${order.orderDate}</td>
                                            <td>${order.tripCost}</td>
                                            <td>${order.distance}</td>
                                            <td>${order.startPoint.get()}</td>
                                            <td>${order.destinationPoint.get()}</td>
                                            <td>${order.passenger.get().firstName}</td>
                                            <td>${order.passenger.get().secondName}</td>
                                            <td>${order.passenger.get().phoneNumber}</td>
                                            <td>${order.passenger.get().rating}</td>
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
