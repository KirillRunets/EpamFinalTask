<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/authentication/login/logIn" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
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
            <li ><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="label.homePage" bundle="${rb}" /></a></li>
            <li class="active"> <a href="#"><fmt:message key="label.currentPage" bundle="${rb}" /></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
    </div>
</nav>
<div class="form">
    <form id="signInForm" name="signInForm" action="${pageContext.request.contextPath}/controller" method="POST">
        <h1><fmt:message key="label.labelForm" bundle="${rb}" /></h1>
        <input type="hidden" name="command" value="login" />
        <div id="hiddenError">
        <br/>
            ${errorEmailPasswordMessage}
        <br/></div>
        <input name="email" id="email" type="text" title="<fmt:message key="label.inputLogin" bundle="${rb}" />" required placeholder="<fmt:message key="label.login" bundle="${rb}" />">
        <input name="password" id="password" type="password" title=<fmt:message key="label.inputPassword" bundle="${rb}" />" required placeholder="<fmt:message key="label.password" bundle="${rb}" />">
        <a href="${pageContext.request.contextPath}/jsp/authentication/signUp.jsp"><fmt:message key="label.signUp" bundle="${rb}" /></a>
        <button type="submit" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
    </form>
</div>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
</body>
</html>