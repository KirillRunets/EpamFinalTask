<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/ban/ban" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message key="label.ban" bundle="${rb}"/></title>
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
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/jsp/admin/admin_home.jsp"><fmt:message key="label.home"
                                                                                                   bundle="${rb}"/></a>
            </li>
            <li class="active"><a href="#"><fmt:message key="label.ban" bundle="${rb}"/></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.LogOut" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
<div class="form" id="ban-form">
    <p>${sessionScope.ban}</p>

    <h1><fmt:message key="label.ban" bundle="${rb}"/></h1>
    <c:if test="${empty sessionScope.ban}">
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="create_ban"/>
            <div class="hiddenError">
                <br/>${requestScope.errorLabel}<br/>
            </div>
            <label><fmt:message key="label.banType" bundle="${rb}"/></label>
            <input name="banType" type="text" pattern='([A-Z][a-z\s]+)|([А-ЯІЎЁ][а-яіўё\s]+)' required title='<fmt:message key="label.banTypeTitle"  bundle="${rb}"/>' placeholder=<fmt:message key="label.banTypePlaceholder" bundle="${rb}"/>>
            <label><fmt:message key="label.banDescription" bundle="${rb}"/></label>
            <input name="banDescription" type="text" pattern='([A-Z][a-z\s]+)|([А-ЯІЎЁ][а-яіўё\s]+)' required title='<fmt:message key="label.banDescriptionTitle"  bundle="${rb}"/>' placeholder=<fmt:message key="label.banDescriptionPlaceholder" bundle="${rb}"/>>
            <div class="button-container">
                <button id="btn-load3" class="button-small"><fmt:message key="label.submit" bundle="${rb}"/></button>
            </div>
        </form>
    </c:if>
    <c:if test="${not empty sessionScope.ban}">
        <form id="notEmptyForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="update_ban"/>
            <input type="hidden" name="ban_id" value=""/>
            <div class="hiddenError">
                <br/>${requestScope.errorLabel}<br/>
            </div>
            <label><fmt:message key="label.banType" bundle="${rb}"/></label>
            <input name="banType" type="text" value="${sessionScope.ban.banType}" pattern='([A-Z][a-z]+)|([А-ЯІЎЁ][а-яіўё]+)' required title='<fmt:message key="label.banTypeTitle"  bundle="${rb}"/>' placeholder=<fmt:message key="label.banTypePlaceholder" bundle="${rb}"/>>
            <label><fmt:message key="label.banDescription" bundle="${rb}"/></label>
            <input name="banDescription" type="text" value="${sessionScope.ban.banDescription}" pattern='([A-Z][a-z]+)|([А-ЯІЎЁ][а-яіўё]+)' required title='<fmt:message key="label.banDescriptionTitle"  bundle="${rb}"/>' placeholder=<fmt:message key="label.banDescriptionPlaceholder" bundle="${rb}"/>>
            <div class="button-container">
                <button class="button-small"><fmt:message key="label.submit" bundle="${rb}"/></button>
            </div>
        </form>
    </c:if>
</div>
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
