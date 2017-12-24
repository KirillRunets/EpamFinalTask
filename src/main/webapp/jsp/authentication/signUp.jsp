<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="lang" scope="session" />
<fmt:setBundle basename="property/authentication/signup/signUp" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.title"/></title>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="label.homePage"  bundle="${rb}"/></a></li>
            <li class="active"> <a href="#"><fmt:message key="label.current"  bundle="${rb}"/></a></li>
        </ul>
    </div>
</nav>
<div class="form">
    <div id="signUpDriver">
        <form id="signUpForm" action="/" method="POST">
            <h1><fmt:message key="label.current"  bundle="${rb}"/></h1>
            <p id="hiddenError" class="hidden"><fmt:message key="label.error"  bundle="${rb}"/></p>
            <div class="top-row">
                <div class="field-wrap">
                    <label><fmt:message key="label.firstName"  bundle="${rb}"/></label>
                    <input  name="firstName" type="text" id="firstName" required placeholder=<fmt:message key="label.firstNamePlaceholder"  bundle="${rb}"/>>
                </div>
                <div class="field-wrap">
                    <label><fmt:message key="label.secondName"  bundle="${rb}"/></label>
                    <input  name="secondName" type="text" id="secondName" required placeholder=<fmt:message key="label.secondNamePlaceholder"  bundle="${rb}"/>>
                </div>
            </div>
            <label>E-mail:</label>
            <input  name="email" type="email" id="email" required placeholder=<fmt:message key="label.emailPlaceholder"  bundle="${rb}"/>>
            <label><fmt:message key="label.password"  bundle="${rb}"/></label>
            <input name="password" id="password" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title=<fmt:message key="label.passwordPatternMessage"  bundle="${rb}"/> required placeholder=<fmt:message key="label.password"  bundle="${rb}"/>>
            <button id="submitButton" type="submit" class="button button-block"><fmt:message key="label.submit"   bundle="${rb}"/></button>
        </form>
    </div>


</div>
<footer class="end_footer footer navbar-inverse">
    <div class="container">
        <div class="col-md-12">
            <div class="footer-description">
                <nav>
                    <a href=""><fmt:message key="label.homePage" bundle="${rb}" /></a>
                </nav>
            </div>
            <div class="icons">
                <ul class="social-icons">
                    <li><a href="" class="social-icon"> <i class="fa fa-facebook"></i></a></li>
                    <li><a href="" class="social-icon"> <i class="fa fa-twitter"></i></a></li>
                    <li><a href="" class="social-icon"> <i class="fa fa-linkedin"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>
</body>
</html>