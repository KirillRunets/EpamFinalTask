<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}"/>
<fmt:setBundle basename="property/admin/driver_all_info" var="rb" />
<%@ page isELIgnored="false"%>
<html>
<head>
    <title><fmt:message key="label.title" bundle="${rb}" /> </title>
</head>
<body>

    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>
