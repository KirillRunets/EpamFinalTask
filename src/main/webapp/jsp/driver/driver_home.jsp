<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="customtag" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/driver/driver_home" var="rb"/>
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
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.LogOut" bundle="${rb}"/></button>
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
<c:import url="${pageContext.request.contextPath}/jsp/driver/driver_sidebar.jsp"/>
<div id="locale" data-prodnumber="${sessionScope.locale}"></div>
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
                            <i class="fa fa-tachometer" aria-hidden="true"></i>${sessionScope.USER.tripAmount} </td>
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
                            <p>${sessionScope.USER.account.accountAmount} <fmt:message key="label.systemСost" bundle="${rb}"/></p>
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
<c:if test="${not empty sessionScope.newOrder}">
    <div class="static-modal">
        <div class="modal-content">
            <form id="orderForm" action="${pageContext.request.contextPath}/controller" method="POST">
                <h1 class="white"><fmt:message key="label.newOrder" bundle="${rb}"/></h1>
                <input type="hidden" name="command" id="driver_order_id" value="">
                <table class="table table-bordered" id="modal-table" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th><fmt:message key="label.date" bundle="${rb}"/></th>
                        <th><fmt:message key="label.tripCost" bundle="${rb}"/></th>
                        <th><fmt:message key="label.distance" bundle="${rb}"/></th>
                        <th><fmt:message key="label.departurePoint" bundle="${rb}"/></th>
                        <th><fmt:message key="label.destinationPoint" bundle="${rb}"/></th>
                        <th><fmt:message key="label.passengerFirstName" bundle="${rb}"/></th>
                        <th><fmt:message key="label.passengerSecondName" bundle="${rb}"/></th>
                        <th><fmt:message key="label.passengerPhoneNumber" bundle="${rb}"/></th>
                        <th><fmt:message key="label.passengerRating" bundle="${rb}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="line" id="${sessionScope.newOrder.id}">
                        <td><fmt:formatDate value="${sessionScope.newOrder.orderDate}"/></td>
                        <td>${sessionScope.newOrder.tripCost}<fmt:message key="label.systemСost" bundle="${rb}"/></td>
                        <td>${sessionScope.newOrder.distance}<fmt:message key="label.systemDistance"
                                                                          bundle="${rb}"/></td>
                        <td>${sessionScope.newOrder.startPoint}</td>
                        <td>${sessionScope.newOrder.destinationPoint}</td>
                        <td>${sessionScope.newOrder.passenger.firstName}</td>
                        <td>${sessionScope.newOrder.passenger.secondName}</td>
                        <td>${sessionScope.newOrder.passenger.phoneNumber}</td>
                        <td>
                            <div class="static-rating">
                                <span class="fa fa-star-o" data-rating="1"></span>
                                <span class="fa fa-star-o" data-rating="2"></span>
                                <span class="fa fa-star-o" data-rating="3"></span>
                                <span class="fa fa-star-o" data-rating="4"></span>
                                <span class="fa fa-star-o" data-rating="5"></span>
                                <input type="hidden" name="whatever1" class="rating-value" value="${sessionScope.newOrder.passenger.rating}">
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <c:if test="${sessionScope.newOrder.confirmed == false}">
                    <div class="button-container">
                        <button id="btn-load1" class="button-small" onclick="loadCommand('confirm')"><fmt:message
                                key="label.confirm" bundle="${rb}"/></button>
                        <button id="btn-load2" class="button-small" onclick="deleteCommand('orderByDriver')">
                            <fmt:message key="label.revoke" bundle="${rb}"/></button>
                    </div>
                </c:if>
                <c:if test="${sessionScope.newOrder.confirmed == true}">
                    <label class="white"><fmt:message key="label.confirmed" bundle="${rb}"/></label>
                    <td><input type="checkbox" disabled checked/></td>
                    <label class="white"><fmt:message key="label.completed" bundle="${rb}"/></label>
                    <td><input type="checkbox" id="completed" onclick="loadCommand('complete')" name="completed"
                               value="true"/></td>
                    <c:if test="${sessionScope.newOrder.paid == true}">
                        <label class="white"><fmt:message key="label.paid" bundle="${rb}"/></label>
                        <td><input type="checkbox" disabled checked/></td>
                    </c:if>
                    <c:if test="${sessionScope.newOrder.paid == false}">
                        <label class="white"><fmt:message key="label.paid" bundle="${rb}"/></label>
                        <td><input type="checkbox" disabled/></td>
                    </c:if>
                </c:if>
            </form>
            <button class="button-small"  id="modal-button"><fmt:message key="label.rate" bundle="${rb}" /></button>
        </div>
    </div>
</c:if>
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <c:import url="${pageContext.request.contextPath}/jsp/modal_rate.jsp"/>
    </div>
</div>
<%--<c:if test="${sessionScope.completed == true}">
    <div id="myModal" class="static-modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2 class="white">Оцените пользователя</h2>
            <form action="${pageContext.request.contextPath}/controller" method="POST">
                <input type="hidden" name="command" value="rate_user">
                <input type="hidden" name="passenger_id" value="rate_user">
                <input type="hidden" name="rating" id="rating" value="">
                <div class="white star-rating">
                    <span class="fa fa-star-o" data-rating="1"></span>
                    <span class="fa fa-star-o" data-rating="2"></span>
                    <span class="fa fa-star-o" data-rating="3"></span>
                    <span class="fa fa-star-o" data-rating="4"></span>
                    <span class="fa fa-star-o" data-rating="5"></span>
                    <input type="hidden" name="whatever1" class="rating-value" value="${sessionScope.newOrder.passenger.rating}">
                </div>
                <button type="submit" onclick="setValue()" class="button-small">Tab</button>
            </form>

        </div>
    </div>
</c:if>--%>
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
<script>
</script>
</body>
</html>
