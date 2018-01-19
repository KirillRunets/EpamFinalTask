<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/driver/car_profile" var="rb"/>
<%@ page isELIgnored="false" %>
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
            <li class="active"><a href="#"><fmt:message key="label.car_profile" bundle="${rb}"/></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.logOut" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
<section class="my-section">
    <div class="content-wrapper">
        <div class="container-fluid">
            <c:if test="${empty sessionScope.USER.car}">
                <c:if test="${empty sessionScope.adminCarList}">
                    <form id="commandForm" action="${pageContext.request.contextPath}/controller" method="POST">
                        <h2><fmt:message key="label.emptyCar" bundle="${rb}"/></h2>
                        <input type="hidden" name="command" value="find_all_valid_cars">
                        <input type="submit" class="button button-small" id="click" value="<fmt:message key="label.addCar" bundle="${rb}"/> "/>
                    </form>
                </c:if>
                <c:if test="${not empty sessionScope.adminCarList}">
                    <div class="driver-form">
                        <form id="driverForm" action="${pageContext.request.contextPath}/controller" method="POST">
                            <h2><fmt:message key="label.labelForm" bundle="${rb}"/></h2>
                            <div id="hiddenError">
                                <br/>
                                    ${requestScope.errorLabel}
                                <br/>
                            </div><input type="hidden" name="command" value="add_car">
                            <label><fmt:message key="label.carList" bundle="${rb}"/></label>
                            <select id="carListId" name="mark_model" onchange="setCarFormData()">
                                <option><fmt:message key="label.chooseCar" bundle="${rb}"/></option>
                                <c:forEach var="car" items="${sessionScope.adminCarList}">
                                    <option value="${car.mark} ${car.model} ${car.releaseDate}">${car.mark} ${car.model}</option>
                                </c:forEach>
                            </select>
                            <div class="top-row">
                                <div class="field-wrap">
                                    <label><fmt:message key="label.release_date" bundle="${rb}"/> </label>
                                    <input type="date" name="release_date" id="release_date_id" value="" required>
                                </div>
                                <div class="field-wrap">
                                    <label><fmt:message key="label.licensePlate" bundle="${rb}"/> </label>
                                    <input type="text" name="licensePlate" placeholder="0001 KP-5" required>
                                </div>
                            </div>
                            <button type="submit" class="button button-block"><fmt:message key="label.submit"
                                                                                           bundle="${rb}"/>
                        </form>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${not empty sessionScope.USER.car}">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="table-responsive">
                            <form action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" id="car_command_id" value="">
                                <input type="hidden" name="car_id" value="${sessionScope.USER.car.id}">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th><fmt:message key="label.mark" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.model" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.release_date" bundle="${rb}"/> </th>
                                        <th><fmt:message key="label.licensePlate" bundle="${rb}"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="line">
                                        <td>${sessionScope.USER.car.mark}</td>
                                        <td>${sessionScope.USER.car.model}</td>
                                        <td>${sessionScope.USER.car.releaseDate}</td>
                                        <td>${sessionScope.USER.car.licensePlate}</td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="button-container">
                                    <button id="btn-load1" class="button-small" onclick="deleteCommand('car');"><fmt:message key="label.delete" bundle="${rb}"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</section>
<c:import url="${pageContext.request.contextPath}/jsp/driver/driver_sidebar.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery.mask.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin-datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
