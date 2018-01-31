<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/admin_transaction_list" var="rb"/>
<%@ taglib prefix="number" uri="customtag" %>
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
            <li class="active"><a href="#"><fmt:message key="label.transactions" bundle="${rb}"/></a></li>
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
<section class="my-section">
    <div class="wrap">

        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="table-responsive">
                            <form action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" value="rollback_transaction">
                                <input type="hidden" name="transactionId" id="transactionId" value="">
                                <h1><fmt:message key="label.transactions" bundle="${rb}"/></h1>
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th><fmt:message key="label.from" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.to" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.amount" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.date" bundle="${rb}"/></th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th><fmt:message key="label.from" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.to" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.amount" bundle="${rb}"/></th>
                                        <th><fmt:message key="label.date" bundle="${rb}"/></th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach items="${sessionScope.transactionList}" var="transaction">
                                        <tr class="line" id="${transaction.id}">
                                            <td>${transaction.from.id}</td>
                                            <td>${transaction.to.id}</td>
                                            <td><number:numberFormatterTag format="##.00"
                                                                           number="${transaction.amount}"/><fmt:message
                                                    key="label.systemСost" bundle="${rb}"/></td>
                                            <td><fmt:formatDate value="${transaction.date}"
                                                                pattern="dd.MM.yyyy HH:mm:ss"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="button-container">
                                    <button id="btn-load1" class="button-small"
                                            onclick="loadCommand('rollback_transaction');"><fmt:message
                                            key="label.rollback" bundle="${rb}"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<c:if test="${param.errorLabel == true}">
    <div id="snackbar" class="show">Something went wrong</div>
</c:if>
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
