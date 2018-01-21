<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/passenger/location" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
</head>
<body class="custom-body">
<div class="form" id="ban-form">
    <form name="changePasswordForm" action="${pageContext.request.contextPath}/controller" method="POST">
        <h1><fmt:message key="label.labelForm" bundle="${rb}" /></h1>
        <input type="hidden" name="command" value="calculate_order_data" >
        <div class="top-row">
            <div class="field-wrap">
                <label><fmt:message key="label.latitude" bundle="${rb}"/></label>
                <input name="latitude" type="number" min="-1000"  max="1000" pattern='[\d]+[.]?[\d]+' required>
            </div>
            <div class="field-wrap">
                <label><fmt:message key="label.longitude" bundle="${rb}"/></label>
                <input name="longitude" type="number" min="-1000"  max="1000" pattern='[\d]+[.]?[\d]+' required >
            </div>
        </div>
        <select class="traffic" name="traffic" required>
            <option value="city"><fmt:message key="label.tripType" bundle="${rb}"/></option>
            <option value="city"><fmt:message key="label.city" bundle="${rb}"/></option>
            <option value="highway"><fmt:message key="label.highway" bundle="${rb}"/></option>
        </select>
        <button id="change-button" type="submit" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
    </form>
</div>
</body>
</html>
