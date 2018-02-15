<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/passenger/location" var="rb"/>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/map.css">
</head>
<body class="custom-body">
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
<c:import url="${pageContext.request.contextPath}/jsp/passenger/passenger_sidebar.jsp"/>
<section class="my-section">
    <div class="content-wrapper">
        <input id="pac-input" class="controls" required type="text" placeholder="<fmt:message key="label.search" bundle="${rb}"/>">
        <div id="map"></div>
        <form id="map-form" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="order_data">
            <input type="hidden" name="distance" id="distance_id" value="">
            <input type="hidden" name="duration" id="duration_id" value="">
            <input type="hidden" name="destination" id="destination_id" value="">
            <input type="hidden" name="departure" id="departure_id" value="">
            <button id="map-button" class="map-button"  type="submit" onclick="go('map-form')"><fmt:message key="label.submit" bundle="${rb}"/></button>
        </form>
    </div>
</section>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
<script src="${pageContext.request.contextPath}/js/map.js">
    initMap();
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdNg3rdFK7l2jePnjzXgOO5589qLxm1WU&libraries=places&callback=initMap">
</script>
</body>
</html>
