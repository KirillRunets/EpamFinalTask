<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/passenger_all_info" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}" /></title>
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
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="${pageContext.request.contextPath}/jsp/admin/admin_home.jsp"><fmt:message key="label.home" bundle="${rb}"/></a></li>
            <li class="active"><a href="#"><fmt:message key="label.title" bundle="${rb}"/></a></li>
        </ul>
        <div class="top-lang-list">
            <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        </div>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message key="label.LogOut" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
<section class="my-section">
    <div class="wrap">

        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Data Table Example
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <form name="driverListForm" action="${pageContext.request.contextPath}/controller"
                                  method="POST">
                                <input type="hidden" name="command" id="user_command_id" value="">
                                <input type="hidden" name="user_id" id="user_id" value="">
                                <input type="hidden" name="user_role" value="passenger">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <h2><fmt:message key="label.passengerInformation" bundle="${rb}"/></h2>
                                        <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.email" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${passengerList}" var="passenger">
                                    <tr class="line" id="${passenger.id}">
                                        <td>${passenger.firstName}</td>
                                        <td>${passenger.secondName}</td>
                                        <td>${passenger.email}</td>
                                        <td><fmt:formatDate value="${passenger.birthDate}" /></td>
                                        <td>${passenger.phoneNumber}</td>
                                        <td>${passenger.rating}</td>
                                        <td>${passenger.tripAmount}</td>
                                    </tr>
                                    </c:forEach>
                                </table>
                                <div class="button-container">
                                    <button id="btn-load1" class="button-small" onclick="deleteCommand('user');"><fmt:message key="label.delete" bundle="${rb}"/></button>
                                    <button id="btn-load2" class="button-small" onclick="loadCommand('user');"><fmt:message key="label.edit" bundle="${rb}"/></button>
                                    <button id="ban-button" class="button-small" onclick="loadCommand('ban')"><fmt:message key="label.ban" bundle="${rb}"/></button>
                                </div>
                            </form>
                        </div>
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
