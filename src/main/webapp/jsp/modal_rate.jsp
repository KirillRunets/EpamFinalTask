<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/admin/edit_user" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
</head>
<body>
<div class="star-rating">
    <span class="fa fa-star-o" data-rating="1"></span>
    <span class="fa fa-star-o" data-rating="2"></span>
    <span class="fa fa-star-o" data-rating="3"></span>
    <span class="fa fa-star-o" data-rating="4"></span>
    <span class="fa fa-star-o" data-rating="5"></span>
    <input type="hidden" name="whatever1" class="rating-value" value="">
</div>
</body>
</html>
