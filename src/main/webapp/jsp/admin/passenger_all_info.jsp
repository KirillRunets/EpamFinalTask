<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/passenger_all_info" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}" /></title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="${pageContext.request.contextPath}/jsp/admin/admin_home.jsp"><fmt:message key="label.home" bundle="${rb}"/></a></li>
            <li class="active"><a href="#"><fmt:message key="label.title" bundle="${rb}"/></a></li>
        </ul>
        <div class="top-lang-list">
            <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        </div>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message key="label.LogOut" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
<section class="my-section">
    <div class="container">
        <table class="table">
            <h2><fmt:message key="label.passengerInformation" bundle="${rb}"/></h2>
            <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
            <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
            <th><fmt:message key="label.email" bundle="${rb}"/></th>
            <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
            <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
            <th><fmt:message key="label.rating" bundle="${rb}"/></th>
            <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
            <c:forEach items="${passengerList}" var="passenger">
                <tr class="line" id="${passenger.id}">
                    <td>${passenger.firstName}</td>
                    <td>${passenger.secondName}</td>
                    <td>${passenger.email}</td>
                    <td>${passenger.birthDate}</td>
                    <td>${passenger.phoneNumber}</td>
                    <td>${passenger.rating}</td>
                    <td>${passenger.tripAmount}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="button-container">
            <button id="btn-load2" class="button-small" onclick="reLoad('/controller?command=load_edit_user&user_id=')"><fmt:message key="label.edit" bundle="${rb}"/></button>
            <button id="btn-load1" class="button-small" onclick="reLoad('/controller?command=delete_user&user_role=passenger&user_id=')"><fmt:message key="label.delete" bundle="${rb}"/></button>
            <button id="ban-button" class="button-small" onclick="reLoad('/controller?command=fill_ban_form&user_id=')"><fmt:message key="label.ban" bundle="${rb}"/></button>
        </div>
    </div>
</section>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
