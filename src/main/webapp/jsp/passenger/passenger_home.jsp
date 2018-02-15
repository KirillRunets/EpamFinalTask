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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/loader.css">

</head>
<body class="custom-body" onload="init(${sessionScope.tripAmountStatistics})">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#"><fmt:message key="label.home" bundle="${rb}"/></a></li>
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
                        <th><fmt:message key="label.accountAmount" bundle="${rb}"/></th>
                    </tr>
                    <tr>
                        <td>
                            <i class="fa fa-tachometer" aria-hidden="true"></i> ${sessionScope.USER.tripAmount}
                        </td>
                        <td>
                            <div class="static-rating">
                                <span class="fa fa-star-o" data-rating="1"></span>
                                <span class="fa fa-star-o" data-rating="2"></span>
                                <span class="fa fa-star-o" data-rating="3"></span>
                                <span class="fa fa-star-o" data-rating="4"></span>
                                <span class="fa fa-star-o" data-rating="5"></span>
                                <input type="hidden" name="whatever1" class="rating-value" value="${sessionScope.USER.rating}">
                            </div>
                        </td>
                        <td>
                            <p>${sessionScope.USER.account.accountAmount}</p>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" value="load_map">
                                <button class="button button-small" ><fmt:message key="label.makeOrder" bundle="${rb}"/></button>
                            </form>
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
<c:if test="${sessionScope.newOrder.completed == true}">
    <div id="myModal" class="static-modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 class="white">Оцените пользователя</h2>
            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="rate_user">
                <input type="hidden" name="rating" id="rating" value="">
                <div class="white star-rating">
                    <span class="fa fa-star-o" data-rating="1"></span>
                    <span class="fa fa-star-o" data-rating="2"></span>
                    <span class="fa fa-star-o" data-rating="3"></span>
                    <span class="fa fa-star-o" data-rating="4"></span>
                    <span class="fa fa-star-o" data-rating="5"></span>
                </div>
                <button type="submit" onclick="setValue()" class="button-small">Rate</button>
            </form>
        </div>
    </div>
</c:if>
<c:if test="${sessionScope.newOrder.confirmed == false && sessionScope.newOrder.paid == false}">
    <div class="static-modal">
        <div class="modal-content">
            <div class="loader"></div>
            <p><fmt:message key="label.wait" bundle="${rb}" /></p>
        </div>
    </div>
</c:if>
<%--<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <c:import url="map.jsp" />
    </div>
</div>--%>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>

<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/chart/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
<script src="${pageContext.request.contextPath}/js/rating.js"></script>
<script src="${pageContext.request.contextPath}/js/chart.js"></script>
</body>
</html>