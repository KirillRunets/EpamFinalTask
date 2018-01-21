<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/passenger/passenger_home" var="rb"/>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/passenger.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/form_style.css">
</head>
<body class="custom-body" onload="init(${sessionScope.tripAmountStatistics})">
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
        </div>
    </nav>
    <div id="locale" data-prodnumber="${sessionScope.locale}" ></div>
    <c:import url="${pageContext.request.contextPath}/jsp/passenger/passenger_sidebar.jsp"/>
    <section class="my-section">
        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="statistics">
                    <table id="stat-table">
                        <tr>
                            <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
                            <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                        </tr>
                        <tr>
                            <td>
                                <i class="fa fa-tachometer" aria-hidden="true"></i> ${sessionScope.USER.tripAmount}
                            </td>
                            <td>
                                <p>${sessionScope.USER.rating}</p>
                            </td>
                            <td>
                                <button class="button button-small" id="modal-button"><fmt:message key="label.makeOrder" bundle="${rb}"/></button>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-area-chart"></i> <fmt:message key="label.statistics" bundle="${rb}"/></div>
                    <div class="card-body">
                        <canvas id="myAreaChart" width="100%" height="30"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <c:import url="${pageContext.request.contextPath}/jsp/passenger/location_modal.jsp"/>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/chart/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/chart.js"></script>
    <script src="${pageContext.request.contextPath}/js/load.js"></script>
    <script src="${pageContext.request.contextPath}/js/modal.js"></script>
</body>
</html>
