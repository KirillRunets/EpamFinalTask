<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/error/error_user_notification" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}"/></title>
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
                <li ><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="label.home" bundle="${rb}"/></a></li>
            </ul>
            <div class="top-lang-list">
                <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
            </div>
        </div>
    </nav>
    <div class="form">
        <h1><fmt:message key="label.title" bundle="${rb}"/></h1>
            <table id="ban_table" class="table">
                <th><fmt:message key="label.unbanDate" bundle="${rb}"/></th>
                <th><fmt:message key="label.banInfo" bundle="${rb}"/></th>
                <tr>
                    <td><p>${requestScope.unbanDate}</p></td>
                    <td><p>${requestScope.banDescription}</p></td>
                </tr>
            </table>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
