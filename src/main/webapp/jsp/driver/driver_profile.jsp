<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/edit_user" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}"/></title>
    <link href="${pageContext.request.contextPath}/lib/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css" />
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sb-admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
</head>
<body class="custom-body">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Buber</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#"><fmt:message key="label.profile" bundle="${rb}" /></a></li>
            </ul>
            <c:import url="${pageContext.request.contextPath}/jsp/change_locale.jsp"/>
            <div class="top-button">
                <button class="button-small" id="aut-btn" onclick="redirectPage('/controller?command=logout')"><fmt:message key="label.logOut" bundle="${rb}"/></button>
            </div>
        </div>
    </nav>
    <c:import url="${pageContext.request.contextPath}/jsp/driver/driver_sidebar.jsp"/>
    <section class="my-section">
        <div class="content-wrapper">
            <div class="container-fluid">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="driver-form">
                            <form id="driverForm" action="${pageContext.request.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" value="edit_user">
                                <input type="hidden" name="user_id" value="${sessionScope.USER.id}">
                                <input type="hidden" name="tripAmount" value="${sessionScope.USER.tripAmount}">
                                <input type="hidden" name="rating" value="${sessionScope.USER.rating}">
                                <input type="hidden" name="user_role" value="${sessionScope.USER.role.roleName}">
                                <h1><fmt:message key="label.current"  bundle="${rb}"/></h1>
                                <input type="hidden" name="command" value="signup" />
                                <div id="hiddenError">
                                    <br/>
                                    ${errorLabel}
                                    <br/>
                                </div>
                                <div class="top-row">
                                    <div class="field-wrap">
                                        <label><fmt:message key="label.firstName"  bundle="${rb}"/></label>
                                        <input type="text"  name="firstName" readonly="readonly" value="${sessionScope.USER.firstName}"/>
                                    </div>
                                    <div class="field-wrap">
                                        <label><fmt:message key="label.secondName"  bundle="${rb}"/></label>
                                        <input type="text" name="secondName" readonly="readonly" value="${sessionScope.USER.secondName}"/>
                                    </div>
                                </div>
                                <div class="top-row">
                                    <div class="field-wrap">
                                        <label>E-mail:</label>
                                        <input  name="email" type="email" value="${sessionScope.USER.email}"  required placeholder="<fmt:message key="label.emailPlaceholder"  bundle="${rb}"/>" />
                                    </div>
                                    <div class="field-wrap">
                                        <label><fmt:message key="label.changePassword"  bundle="${rb}"/></label>
                                        <input type="button" name="changePassword" class="button button-block" id="modal-button" value="<fmt:message key="label.changePassword"  bundle="${rb}"/>" />
                                    </div>
                                </div>
                                <label><fmt:message key="label.birthDate"  bundle="${rb}"/></label>
                                <input name="birthDate" type="date" value="${sessionScope.USER.birthDate}"  required/>
                                <label><fmt:message key="label.phoneNumber"  bundle="${rb}"/></label>
                                <input  name="phoneNumber" id="phoneNumber" type="text" value="${sessionScope.USER.phoneNumber}" required placeholder=<fmt:message key="label.phoneNumber"  bundle="${rb}"/>>
                                <button type="submit" class="button button-block"><fmt:message key="label.submit"   bundle="${rb}"/></button>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <c:import url="${pageContext.request.contextPath}/jsp/change_password.jsp"/>
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
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery.mask.js"></script>
    <script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sb-admin-datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/load.js"></script>
    <script src="${pageContext.request.contextPath}/js/modal.js"></script>
</body>
</html>
