<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="property/admin/admin_home" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/form_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="">Buber</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#"><fmt:message key="label.home" bundle="${rb}" /></a></li>
            </ul>
            <div class="top-lang-list">
                <select id="mySelect" name="locale" onchange="location = this.options[this.selectedIndex].value;">
                    <option disabled selected><fmt:message key="label.language" bundle="${rb}" /></option>
                    <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">Русский</option>
                    <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=be_BY">Мова</option>
                    <option value="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_US">English</option>
                </select>
            </div>
            <div class="top-button">
                <button class="button-small" id="aut-btn"><a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="label.LogOut" bundle="${rb}" /></a> </button>
            </div>
        </div>
    </nav>
    <section class="my-section">
        <div class="container">
            <div class="col-md-4">
                <div class="description-wrapper">
                    <h2><i class="fa fa-user" aria-hidden="true"></i> <fmt:message key="label.user" bundle="${rb}" /></h2>
                    <hr>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/controller?command=find_all_driver" class="social-icon"><i class="fa fa-users" aria-hidden="true"></i> <fmt:message key="label.showDriver"  bundle="${rb}" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/controller?command=find_all_passenger" class="social-icon"><i class="fa fa-users" aria-hidden="true"></i> <fmt:message key="label.showPassenger"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-user-plus" aria-hidden="true"></i> <fmt:message key="label.addUser"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-trash" aria-hidden="true"></i> <fmt:message key="label.deleteUser"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-pencil" aria-hidden="true"></i> <fmt:message key="label.editUser"  bundle="${rb}" /></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4">
                <div class="description-wrapper">
                    <h2><i class="fa fa-taxi" aria-hidden="true"></i> <fmt:message key="label.car" bundle="${rb}" /></h2>
                    <hr>
                    <ul>
                        <li><a href="" class="social-icon"><i class="fa fa-taxi" aria-hidden="true"></i> <fmt:message key="label.showTaxi"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-car" aria-hidden="true"></i> <fmt:message key="label.addTaxi"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-minus-circle" aria-hidden="true"></i> <fmt:message key="label.deleteTaxi"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-cog" aria-hidden="true"></i> <fmt:message key="label.editTaxi"  bundle="${rb}" /></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4">
                <div class="description-wrapper">
                    <h2><i class="fa fa-database" aria-hidden="true"></i> <fmt:message key="label.order" bundle="${rb}" /></h2>
                    <hr>
                    <ul>
                        <li><a href="" class="social-icon"><i class="fa fa-users" aria-hidden="true"></i> <fmt:message key="label.showOrder"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-credit-card" aria-hidden="true"></i> <fmt:message key="label.payOrder"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-trash" aria-hidden="true"></i> <fmt:message key="label.deleteOrder"  bundle="${rb}" /></a></li>
                        <li><a href="" class="social-icon"><i class="fa fa-pencil" aria-hidden="true"></i> <fmt:message key="label.editOrder"  bundle="${rb}" /></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>


    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
</body>
</html>
