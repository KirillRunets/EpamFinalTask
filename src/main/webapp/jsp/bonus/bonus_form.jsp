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
            <li class="active"><a href="#"><fmt:message key="label.bonus" bundle="${rb}"/></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message
                    key="label.LogOut" bundle="${rb}"/></button>
        </div>
    </div>
</nav>
<div class="form" id="ban-form">
    <h1><fmt:message key="label.bonus" bundle="${rb}"/></h1>
    <c:if test="${empty sessionScope.bonus}">
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="create_bonus"/>
            <div class="hiddenError">
                <br/>${requestScope.errorLabel}<br/>
            </div>
            <label><fmt:message key="label.bonusType" bundle="${rb}"/></label>
            <input name="bonusType" type="text" pattern='([A-Z][a-z\s]+)|([А-ЯІЎЁ][а-яіўё\s]+)' required title='<fmt:message key="label.banTypeTitle"  bundle="${rb}"/>' placeholder=<fmt:message key="label.banTypePlaceholder" bundle="${rb}"/>>
            <label><fmt:message key="label.bonusDescription" bundle="${rb}"/></label>
            <textarea name="bonusDescription" type="text" placeholder=<fmt:message key="label.bonusDescriptionPlaceholder" bundle="${rb}"/> required></textarea>
            <div class="button-container">
                <button id="btn-load3" class="button-small"><fmt:message key="label.submit" bundle="${rb}"/></button>
            </div>
        </form>
    </c:if>
    <c:if test="${not empty sessionScope.bonus}">
        <form id="notEmptyForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="update_bonus"/>
            <input type="hidden" name="bonus_id" value="${sessionScope.bonus.id}"/>
            <div class="hiddenError">
                <br/>${requestScope.errorLabel}<br/>
            </div>
            <label><fmt:message key="label.bonusType" bundle="${rb}"/></label>
            <input name="bonusType" type="text" value="${sessionScope.bonus.bonusType}" pattern='([A-Z][a-z\s]+)|([А-ЯІЎЁ][а-яіўё\s]+)' required placeholder=<fmt:message key="label.bonusTypePlaceholder" bundle="${rb}"/>>
            <label><fmt:message key="label.bonusDescription" bundle="${rb}"/></label>
            <textarea name="bonusDescription" type="text" placeholder=<fmt:message key="label.bonusDescriptionPlaceholder" bundle="${rb}"/> required>${sessionScope.bonus.bonusDescription}</textarea>
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
