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
</head>
<body class="custom-body" onload='init(${fn:escapeXml(requestScope.JSON)})'>
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
    <%--<section class="my-section">
        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
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
                                    <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.mark" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.model" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.release_date" bundle="${rb}"/></th>
                                    <th><fmt:message key="label.licensePlate" bundle="${rb}"/></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${sessionScope.driverList}" var="driver">
                                    <tr class="line" id="${driver.id}">
                                        <td>${driver.firstName}</td>
                                        <td>${driver.secondName}</td>
                                        <td>${driver.rating}</td>
                                        <td>${driver.birthDate}</td>
                                        <td>${driver.phoneNumber}</td>
                                        <td>${driver.tripAmount}</td>
                                        <td>${driver.car.mark}</td>
                                        <td>${driver.car.model}</td>
                                        <td>${driver.car.releaseDate}</td>
                                        <td>${driver.car.licesePlate}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>--%>
    <section class="my-section">
        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="trip-data">
                    <form action="${pageContext.request.contextPath}/controller" method="POST">
                        <input type="hidden" name="command" value="PASSENGER_TRIP_JSON_STAT">
                        <input type="submit" value="submit"/>
                    </form>
                    <c:out value="${requestScope.JSON}"/>
                </div>
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-area-chart"></i> Area Chart Example</div>
                    <div class="card-body">
                        <canvas id="myAreaChart" width="100%" height="30"></canvas>
                    </div>
                    <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/chart/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/chart.js"></script>
    <script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
