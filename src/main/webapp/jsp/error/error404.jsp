<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/error/error" var="rb"/>
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
<body class="custom-body">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/jsp/main.jsp"><fmt:message key="title.home"
                                                                                       bundle="${rb}"/></a></li>
            <li class="active"><a href="#"><fmt:message key="title.error404" bundle="${rb}"/></a></li>
        </ul>
    </div>
</nav>
<div class="wrapper">
    <div class="container">
        <div class="row">
            <div class="error">

                <div class="card z-depth-4">
                    <h1><fmt:message key="title.pageNotFound" bundle="${rb}"/></h1>
                    <p><fmt:message key="title.description" bundle="${rb}"/></p>
                </div>
            </div>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
</body>
</html>
