<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/all_ban_user" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.transactions" bundle="${rb}"/></title>
    <link href="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css"/>
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sb-admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
</head>
<body class="custom-body">
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
    <div class="wrap">
        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="card mb-3">
                        <div class="table-responsive">
                            <form action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" id="ban_command_id" value="">
                                <input type="hidden" name="user_id" id="user_id" value="">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.email" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.role" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.car" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.banDescription" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.unbanDate" bundle="${rb}"/></th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th><fmt:message key="label.firstName" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.secondName" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.email" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.birthDate" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.phoneNumber" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.rating" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.tripAmount" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.role" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.car" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.banDescription" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.unbanDate" bundle="${rb}"/></th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach items="${userList}" var="user">
                                        <c:if test="${not empty userList}">
                                            <tr class="line" id="${user.id}">
                                                <td>${user.firstName}</td>
                                                <td>${user.secondName}</td>
                                                <td>${user.email}</td>
                                                <td><fmt:formatDate value="${user.birthDate}" /></td>
                                                <td>${user.phoneNumber}</td>
                                                <td>${user.rating}</td>
                                                <td>${user.tripAmount}</td>
                                                <c:if test="${user.role.roleName == 'DRIVER'}">
                                                    <td><fmt:message key="label.driver" bundle="${rb}"/></td>
                                                </c:if>
                                                <c:if test="${user.role.roleName == 'PASSENGER'}">
                                                    <td><fmt:message key="label.passenger" bundle="${rb}"/></td>
                                                </c:if>
                                                <c:if test="${not empty user.car}">
                                                    <td>${user.car.mark} ${user.car.model} </td>
                                                </c:if>
                                                <c:if test="${empty user.car.mark}">
                                                    <td><fmt:message key="label.empty" bundle="${rb}"/></td>
                                                </c:if>
                                                <td>${user.ban.banDescription}</td>
                                                <td><fmt:formatDate value="${user.unBaneDate}" /></td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="button-container">
                                    <button id="btn-load1" class="button-small" onclick="deleteCommand('ban');"><fmt:message key="label.delete" bundle="${rb}"/></button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
</section>
<c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sb-admin-datatables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/load.js"></script>
</body>
</html>
