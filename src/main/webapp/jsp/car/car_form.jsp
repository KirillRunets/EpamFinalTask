<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/edit_car" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css"/>
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
            <li><a href="${pageContext.request.contextPath}/jsp/main.jsp"><fmt:message key="label.home" bundle="${rb}"/></a></li>
            <c:if test="${not empty sessionScope.car.id}">
                <li class="active"><a href="#"><fmt:message key="label.title" bundle="${rb}"/></a></li>
            </c:if>
            <c:if test="${empty sessionScope.car.id}">
                <li class="active"><a href="#"><fmt:message key="label.addTitle" bundle="${rb}"/></a></li>
            </c:if>
        </ul>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.LogOut" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
<div class="form">
    <div id="signUpDriver">
            <c:if test="${not empty sessionScope.car.id}">
                <form id="editForm" name="editForm" action="${pageContext.request.contextPath}/controller" method="POST">
                    <h1><fmt:message key="label.title" bundle="${rb}"/></h1>
                    <input type="hidden" name="command" value="edit_car"/>
                    <input type="hidden" name="car_id" value="${sessionScope.car.id}"/>
                    <div class="top-row">
                        <div class="field-wrap">
                            <label><fmt:message key="label.mark" bundle="${rb}"/></label>
                            <input name="mark" value="${sessionScope.car.mark}" type="text" required placeholder=
                                <fmt:message key="label.firstNamePlaceholder" bundle="${rb}"/>>
                        </div>
                        <div class="field-wrap">
                            <label><fmt:message key="label.model" bundle="${rb}"/></label>
                            <input name="model" value="${sessionScope.car.model}" type="text" required placeholder=<fmt:message key="label.secondNamePlaceholder" bundle="${rb}"/>>
                        </div>
                    </div>
                    <label><fmt:message key="label.release_date" bundle="${rb}"/> </label>
                    <input type="date" class="custom-date" name="release_date"  value="${sessionScope.car.releaseDate}">
                    <button type="submit" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
                </form>
            </c:if>
            <c:if test="${empty sessionScope.car.id}">
                <form id="addForm" name="addForm" action="${pageContext.request.contextPath}/controller" method="POST">
                    <h1><fmt:message key="label.addTitle" bundle="${rb}"/></h1>
                    <input type="hidden" name="command" value="add_car"/>
                    <div class="top-row">
                        <div class="field-wrap">
                            <label><fmt:message key="label.mark" bundle="${rb}"/></label>
                            <input name="mark"type="text" required placeholder=
                                <fmt:message key="label.markPlaceholder" bundle="${rb}"/>>
                        </div>
                        <div class="field-wrap">
                            <label><fmt:message key="label.model" bundle="${rb}"/></label>
                            <input name="model" type="text" required placeholder=<fmt:message key="label.modelPlaceholder" bundle="${rb}"/>>
                        </div>
                    </div>
                    <label><fmt:message key="label.release_date" bundle="${rb}"/> </label>
                    <input type="date" class="custom-date" name="release_date">
                    <button type="submit" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
                </form>
            </c:if>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
