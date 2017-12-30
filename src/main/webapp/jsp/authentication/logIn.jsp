<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/authentication/login/logIn" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
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
            <a class="navbar-brand" href="">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href=""><fmt:message key="label.homePage" bundle="${rb}" /></a></li>
            <li class="active"> <a href="#"><fmt:message key="label.currentPage" bundle="${rb}" /></a></li>
        </ul>
        <div class="top-lang-list">
        <select id="mySelect">
            <option value="ru"><a href="Controller?command=locale">Русский</a></option>
            <option value="be"><a href="Controller?command=locale">Беларуская мова</a></option>
            <option value="en"><a href="Controller?command=locale">English</a></option>
        </select>
    </div>
    </div>
</nav>
<div class="form">
    <form id="signInForm" name="signInForm" action="${pageContext.request.contextPath}/Controller" method="POST">
        <h1><fmt:message key="label.labelForm" bundle="${rb}" /></h1>
        <input type="hidden" name="command" value="login" />
        <input name="email" id="email" type="text" title="<fmt:message key="label.inputLogin" bundle="${rb}" />" required placeholder="<fmt:message key="label.login" bundle="${rb}" />">
        <input name="password" id="password" type="password" title=<fmt:message key="label.inputPassword" bundle="${rb}" />" required placeholder="<fmt:message key="label.password" bundle="${rb}" />">
        <a href=""><fmt:message key="label.signUp" bundle="${rb}" /></a>
        <button type="submit" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
    </form>
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