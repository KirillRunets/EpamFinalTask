<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/all_ban_user" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}"/></title>
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
            <h2><fmt:message key="label.userBannedInfo" bundle="${rb}"/></h2>
            <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
            <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
            <th><fmt:message key="label.email" bundle="${rb}"/></th>
            <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
            <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
            <th><fmt:message key="label.rating" bundle="${rb}"/></th>
            <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
            <c:if test="${not empty driver.car.mark}">
                <th><fmt:message key="label.car" bundle="${rb}"/></th>
            </c:if>
            <th><fmt:message key="label.role" bundle="${rb}"/></th>
            <th><fmt:message key="label.ban" bundle="${rb}"/></th>
            <th><fmt:message key="label.banDescription" bundle="${rb}"/></th>
            <th><fmt:message key="label.unbanDate" bundle="${rb}"/></th>
            <c:forEach items="${userList}" var="user">
                <c:if test="${not empty userList}">
                    <tr class="line" id="${user.id}">
                        <td>${user.firstName}</td>
                        <td>${user.secondName}</td>
                        <td>${user.email}</td>
                        <td>${user.birthDate}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.rating}</td>
                        <td>${user.tripAmount}</td>
                        <td>${user.role.roleName}</td>
                        <c:if test="${not empty user.car.mark}">
                            <td>${user.car.mark}</td>
                        </c:if>
                        <td>${user.ban.banType}</td>
                        <td>${user.ban.banDescription}</td>
                        <td>${user.unBaneDate}</td>
                    </tr>
                </c:if>
                <c:if test="${empty userList}">
                    <tr>
                        <td><fmt:message key="label.emptyList" bundle="${rb}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <div class="button-container">
            <button id="btn-load1" class="button-small" onclick="reLoad('/controller?command=unban_user&user_id=')"><fmt:message key="label.delete" bundle="${rb}"/></button>
        </div>
    </div>
</section>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
