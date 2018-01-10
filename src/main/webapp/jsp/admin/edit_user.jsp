<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/edit_user" var="rb"/>
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
            <li><a href="${pageContext.request.contextPath}/jsp/admin/admin_home.jsp"><fmt:message key="label.home" bundle="${rb}"/></a></li>
            <li class="active"><a href="#"><fmt:message key="label.title" bundle="${rb}"/></a></li>
        </ul>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.logOut" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
<div class="form">
    <div id="signUpDriver">
        <form id="signInForm" name="signInForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <h1><fmt:message key="label.labelForm" bundle="${rb}"/></h1>
            <input type="hidden" name="command" value="update_driver"/>
            <input type="hidden" name="user_id" value="${user.id}"/>
            <div id="hiddenError">
                <br/>
                ${errorEmailPasswordMessage}
                <br/>
            </div>
            <div class="top-row">
                <div class="field-wrap">
                    <label><fmt:message key="label.firstName" bundle="${rb}"/></label>
                    <c:if test="${not empty user.firstName}">
                        <input name="firstName" value="${user.firstName}" type="text" required placeholder=
                            <fmt:message key="label.firstNamePlaceholder" bundle="${rb}"/>>
                    </c:if>
                    <c:if test="${empty user.firstName}">
                        <input name="firstName" value="<fmt:message key="label.empty"  bundle="${rb}"/>" type="text"
                               required placeholder=<fmt:message key="label.firstNamePlaceholder" bundle="${rb}"/>>
                    </c:if>
                </div>
                <div class="field-wrap">
                    <label><fmt:message key="label.secondName" bundle="${rb}"/></label>

                    <c:if test="${not empty user.secondName}">
                        <input name="secondName" value="${user.secondName}" type="text" required placeholder=<fmt:message key="label.secondNamePlaceholder" bundle="${rb}"/>>
                    </c:if>
                    <c:if test="${empty user.secondName}">
                        <input name="secondName" value="<fmt:message key="label.empty"  bundle="${rb}"/>" type="text" required placeholder=<fmt:message key="label.secondNamePlaceholder" bundle="${rb}"/>>
                    </c:if>
                </div>
            </div>
            <label>E-mail:</label>
            <c:if test="${not empty user.email}">
                <input name="email" value="${user.email}" type="text" required placeholder=<fmt:message
                        key="label.emailPlaceholder" bundle="${rb}"/>>
            </c:if>
            <c:if test="${empty user.email}">
                <input name="email" value="<fmt:message key="label.empty"  bundle="${rb}"/>" type="text"
                       required placeholder=<fmt:message key="label.emailPlaceholder" bundle="${rb}"/>>
            </c:if>
            <label><fmt:message key="label.birthDate" bundle="${rb}"/> </label>
            <c:if test="${not empty user.birthDate}">
                <input type="date" name="birthDate"  value="${user.birthDate}">
            </c:if>
            <c:if test="${empty user.birthDate}">
                <input type="date" name="birthDate" value="<fmt:message key="label.empty"  bundle="${rb}"/>">
            </c:if>
            <label><fmt:message key="label.phoneNumber" bundle="${rb}"/> </label>
            <c:if test="${not empty user.phoneNumber}">
                <input name="phoneNumber" value="${user.phoneNumber}" type="text" required placeholder=<fmt:message
                        key="label.phoneNumberPlaceHolder" bundle="${rb}"/>>
            </c:if>
            <c:if test="${empty user.phoneNumber}">
                <input name="phoneNumber" value="<fmt:message key="label.empty"  bundle="${rb}"/>" type="text" required placeholder=<fmt:message
                        key="label.phoneNumberPlaceHolder" bundle="${rb}"/>>
            </c:if>
            <label><fmt:message key="label.rating" bundle="${rb}"/> </label>
            <c:if test="${not empty user.rating}">
                <input name="rating" value="${user.rating}" type="text" required placeholder=<fmt:message
                        key="label.ratingPlaceHolder" bundle="${rb}"/>>
            </c:if>
            <c:if test="${empty user.rating}">
                <input name="rating" value="<fmt:message key="label.empty"  bundle="${rb}"/>" type="text" required placeholder=<fmt:message
                        key="label.ratingPlaceHolder" bundle="${rb}"/>>
            </c:if>

            <label><fmt:message key="label.tripAmount" bundle="${rb}"/> </label>

            <c:if test="${not empty user.tripAmount}">
                <input name="tripAmount" value="${user.tripAmount}" type="text" required placeholder=<fmt:message
                        key="label.tripAmountPlaceHolder" bundle="${rb}"/>>
            </c:if>
            <c:if test="${empty user.tripAmount}">
                <input name="tripAmount" value="<fmt:message key="label.empty"  bundle="${rb}"/>" type="text" required placeholder=<fmt:message
                        key="label.tripAmountPlaceHolder" bundle="${rb}"/>>
            </c:if>
            <select name="user_role" id="userRole">
                <option value="DRIVER"><fmt:message key="label.driver" bundle="${rb}"/></option>
                <option value="PASSENGER"><fmt:message key="label.passenger" bundle="${rb}"/></option>
            </select>
            <button type="submit" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
        </form>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
</body>
</html>
