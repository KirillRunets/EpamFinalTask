<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="property/main/main" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/form_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
</head>
<body>
    <footer class="end_footer footer navbar-inverse">
        <div class="container">
            <div class="col-md-6">
                <div class="footer-description">
                    <p id="copyright"><fmt:message key="label.copyright" bundle="${rb}" /></p>
                </div>
            </div>
            <div class="col-md-6">
                <div class="icons">
                    <ul class="social-icons">
                        <li><a href="" class="social-icon"> <i class="fa fa-facebook"></i></a></li>
                        <li><a href="" class="social-icon"> <i class="fa fa-twitter"></i></a></li>
                        <li><a href="" class="social-icon"> <i class="fa fa-linkedin"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.js"></script>
</body>
</html>
