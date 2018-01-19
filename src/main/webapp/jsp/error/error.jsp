<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/error/error" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css"/>
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
            <li><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="title.home" bundle="${rb}" /></a></li>
            <li class="active"><a href="#"><fmt:message key="title.error" bundle="${rb}" /></a></li>
        </ul>
        <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
    </div>
</nav>
<div class="wrapper">
    <div class="container">
        <div class="row">
            <div class="card z-depth-4">
                <h1><fmt:message key="title.errorLabel" bundle="${rb}" /></h1>

                <table width="auto" border="1" class="table" id="error-table">
                    <tr valign="top">
                        <td width="7%"><b><fmt:message key="title.error" bundle="${rb}" /></b></td>
                        <td>${pageContext.exception}</td>
                    </tr>
                    <tr valign="top">
                        <td><b><fmt:message key="title.url" bundle="${rb}" /> </b></td>
                        <td>${pageContext.errorData.requestURI}</td>
                    </tr>
                    <tr valign="top">
                        <td><b><fmt:message key="title.status_code" bundle="${rb}" /></b></td>
                        <td>${pageContext.errorData.statusCode}</td>
                    </tr>
                    <tr valign="top">
                        <td><b><fmt:message key="title.stack_trace" bundle="${rb}" /></b></td>
                        <td>
                            <c:forEach var="trace"
                                       items="${pageContext.exception.stackTrace}">
                                <p>${trace}</p>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
</body>
</html>
