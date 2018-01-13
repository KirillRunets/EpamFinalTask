<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property/main/change_password" var="rb"/>
<%@ page isELIgnored="false" %>
<html>
<head>
</head>
<body class="custom-body">
    <div class="form" id="ban-form">
        <form id="changePasswordForm" name="changePasswordForm" action="${pageContext.request.contextPath}/controller" method="POST">
            <h1><fmt:message key="label.labelForm" bundle="${rb}" /></h1>
            <div id="hiddenError">
                <br/>
                ${errorLabel}
                <br/>
            </div>
            <input type="hidden" name="command" value="change_password" >
            <input type="hidden" name="user_id" value="${sessionScope.USER.id}">
            <label><fmt:message key="label.oldPassword" bundle="${rb}"/></label>
            <input type="password" name="old_password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title='<fmt:message key="label.passwordPatternMessage"  bundle="${rb}"/>'>
            <label><fmt:message key="label.newPassword" bundle="${rb}"/></label>
            <input type="password" name="new_password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title='<fmt:message key="label.passwordPatternMessage"  bundle="${rb}"/>'>
            <button id="change-button" type="submit" class="button button-block"><fmt:message key="label.submit" bundle="${rb}"/></button>
        </form>
    </div>
</body>
</html>
