<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/authentication/signup/signUp" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="label.homePage"
                                                                                    bundle="${rb}"/></a></li>
            <li class="active"><a href="#"><fmt:message key="label.current" bundle="${rb}"/></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
    </div>
</nav>
<div class="form">
    <div id="signUpDriver">
        <form id="signUpForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <h1><fmt:message key="label.current" bundle="${rb}"/></h1>
            <input type="hidden" name="command" value="signup"/>
            <div id="hiddenError">
                <br/>${requestScope.errorLabel}<br/>
            </div>
            <c:if test="${empty requestScope.USER}">
                <div class="top-row">
                    <div class="field-wrap">
                        <label><fmt:message key="label.firstName" bundle="${rb}"/></label>

                        <input name="firstName" type="text" pattern='([A-Z][a-z]+)|([А-ЯІЎЁ][а-яіўё]+)' required
                               title='<fmt:message key="label.nameTitle"  bundle="${rb}"/>' placeholder=<fmt:message
                                key="label.firstNamePlaceholder" bundle="${rb}"/>>
                    </div>
                    <div class="field-wrap">
                        <label><fmt:message key="label.secondName" bundle="${rb}"/></label>
                        <input name="secondName" type="text" pattern='([A-Z][a-z]+)|([А-ЯІЎЁ][а-яіўё]+)' required
                               title='<fmt:message key="label.nameTitle"  bundle="${rb}"/>' placeholder=<fmt:message
                                key="label.secondNamePlaceholder" bundle="${rb}"/>>
                    </div>
                </div>

                <label>E-mail:</label>
                <input name="email" type="email" pattern="[A-Za-z\d]+[.]?[a-z0-9]+[@]{1}[a-z]+[(.)][a-z]+"
                       title='<fmt:message key="label.emailTitle"  bundle="${rb}"/>' required placeholder=<fmt:message
                        key="label.emailPlaceholder" bundle="${rb}"/>>

                <label><fmt:message key="label.password" bundle="${rb}"/></label>
                <input name="password" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                       title='<fmt:message key="label.passwordPatternMessage"  bundle="${rb}"/>' required class="required"
                       placeholder=<fmt:message key="label.password" bundle="${rb}"/>>

                <label><fmt:message key="label.role" bundle="${rb}"/></label>
                <select name="user_role" required id="userRole">
                    <option value="DRIVER"><fmt:message key="label.driver" bundle="${rb}"/></option>
                    <option value="PASSENGER"><fmt:message key="label.passenger" bundle="${rb}"/></option>
                </select>
            </c:if>
            <c:if test="${not empty requestScope.USER}">
                <div class="top-row">
                    <div class="field-wrap">
                        <label><fmt:message key="label.firstName" bundle="${rb}"/></label>

                        <input name="firstName" type="text" value="${requestScope.USER.firstName}" pattern='([A-Z][a-z]+)|([А-ЯІЎЁ][а-яіўё]+)' required
                               title='<fmt:message key="label.nameTitle"  bundle="${rb}"/>' placeholder=<fmt:message
                                key="label.firstNamePlaceholder" bundle="${rb}"/>>
                    </div>
                    <div class="field-wrap">
                        <label><fmt:message key="label.secondName" bundle="${rb}"/></label>
                        <input name="secondName" type="text" value="${requestScope.USER.secondName}" pattern='([A-Z][a-z]+)|([А-ЯІЎЁ][а-яіўё]+)' required
                               title='<fmt:message key="label.nameTitle"  bundle="${rb}"/>' placeholder=<fmt:message
                                key="label.secondNamePlaceholder" bundle="${rb}"/>>
                    </div>
                </div>

                <label>E-mail:</label>
                <input name="email" type="email" value="${requestScope.USER.email}" pattern="[A-Za-z\d]+[.]?[a-z0-9]+[@]{1}[a-z]+[(.)][a-z]+"
                       title='<fmt:message key="label.emailTitle"  bundle="${rb}"/>' required placeholder=<fmt:message
                        key="label.emailPlaceholder" bundle="${rb}"/>>

                <label><fmt:message key="label.password" bundle="${rb}"/></label>
                <input name="password" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                       title='<fmt:message key="label.passwordPatternMessage"  bundle="${rb}"/>' required class="required"
                       placeholder=<fmt:message key="label.password" bundle="${rb}"/>>

                <label><fmt:message key="label.role" bundle="${rb}"/></label>
                <select name="user_role" required id="userRole">
                    <option value="DRIVER"><fmt:message key="label.driver" bundle="${rb}"/></option>
                    <option value="PASSENGER"><fmt:message key="label.passenger" bundle="${rb}"/></option>
                </select>
            </c:if>
            <button type="submit" id="submitButton" class="button button-block"><fmt:message key="label.submit"
                                                                                             bundle="${rb}"/></button>
        </form>
    </div>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>