<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/ban_form" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.labelForm" bundle="${rb}"/></title>
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
                <li><a href="${pageContext.request.contextPath}/jsp/admin/admin_home.jsp"><fmt:message key="label.home" bundle="${rb}"/></a></li>
                <li class="active"><a href="#"><fmt:message key="label.labelForm" bundle="${rb}"/></a></li>
            </ul>
            <div class="top-lang-list">
                <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
            </div>
            <div class="top-button">
                <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                        key="label.logout" bundle="${rb}"/></button>
            </div>
        </div>
    </nav>
    <div class="form" id="ban-form">
        <form id="banUserForm" name="banUserForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <h1><fmt:message key="label.labelForm" bundle="${rb}" /></h1>
            <input type="hidden" name="command" value="ban_user">
            <input type="hidden" name="ban_id" id="ban_id" value="">
            <table id="ban_table" class="table">
            <th><fmt:message key="label.banType" bundle="${rb}"/></th>
            <th><fmt:message key="label.banDescription" bundle="${rb}"/></th>
            <c:forEach items="${sessionScope.banList}" var="ban">
                <tr class="line" id="${ban.id}">
                    <td>${ban.banType}</td>
                    <td>${ban.banDescription}</td>
                </tr>
            </c:forEach>
            </table>
            <input type="date"  required name="unbanDate"/>
            <button id="ban-button" type="submit" onclick="banCommand();" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
        </form>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
