<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/passenger/passenger_home" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css"/>
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
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
            <li class="active"><a href="#"><fmt:message key="label.drivers" bundle="${rb}"/></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.logout" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
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
                        <th><fmt:message key="label.currentLocation" bundle="${rb}"/></th>
                    </tr>
                    <tr>
                        <td>
                            ${sessionScope.tripDistance}
                        </td>
                        <td>
                            <p>${sessionScope.tripTime}</p>
                        </td>
                        <td>
                            <p>${sessionScope.tripCost}</p>
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
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.email" bundle="${rb}"/></th>
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
                                <tfoot>
                                <tr>
                                    <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.email" bundle="${rb}"/></th>
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
                                </tfoot>
                                <tbody>
                                <c:forEach items="${sessionScope.priorityDriversQueue}" var="driver">
                                    <tr class="line" id="${driver.id}">
                                        <td>${driver.firstName}</td>
                                        <td>${driver.secondName}</td>
                                        <td>${driver.email}</td>
                                        <td>${driver.birthDate}</td>
                                        <td>${driver.phoneNumber}</td>
                                        <td>${driver.rating}</td>
                                        <td>${driver.tripAmount}</td>
                                        <td>${driver.currentLocation}</td>
                                        <c:if test="${not empty driver.car.mark}">
                                            <td>${driver.car.mark}</td>
                                            <td>${driver.car.model}</td>
                                            <td>${driver.car.releaseDate}</td>
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
                                <button id="btn-load1" class="button-small" onclick="deleteCommand('car');"><fmt:message
                                        key="label.delete" bundle="${rb}"/></button>
                                <button id="btn-load2" class="button-small" onclick="loadCommand('car');"><fmt:message
                                        key="label.edit" bundle="${rb}"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin-datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
