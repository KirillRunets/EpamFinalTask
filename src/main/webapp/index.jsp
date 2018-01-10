<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/authentication/login/logIn" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body class="main-body">
<jsp:forward page="jsp/main.jsp"/>
</body>
</html>
