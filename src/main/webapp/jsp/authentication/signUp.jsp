<%@ page language="java" contentType="text/html; charset=ISO-8859-5" pageEncoding="ISO-8859-5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="true"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/bootstrap-theme.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/form_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/social_icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li class="active"> <a href="#">Sign up</a></li>
        </ul>
    </div>
</nav>
<div class="form">
    <form id="signUpForm" action="" method="POST">
        <h1>SignUp</h1>
        <p id="hiddenError" class="hidden">The login is already exist</p>
        <label>E-mail:</label>
        <input  name="email" type="email" id="email" title="Wrong" required placeholder="Input e-mail">
        <label>Login:</label>
        <input name="login" type="text" id="login" title="Wrong" required placeholder="Input login">
        <label>Password:</label>
        <input name="password" id="password" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required placeholder="Password">
        <input name="matchingPassword" id="matchingPassword" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Matching password is not equals to password" required placeholder="Password">
        <button id="submitButton" type="submit" class="button button-block">SignUp</button>
    </form>
</div>
<div id="message">
    <h3>Password must contain the following:</h3>
    <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
    <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
    <p id="number" class="invalid">A <b>number</b></p>
    <p id="length" class="invalid">Minimum <b>8 characters</b></p>
</div>
<footer class="end_footer footer navbar-inverse">
    <div class="container">
        <div class="col-md-12">
            <div class="footer-description">
                <nav>
                    <a href="">Buber home</a>
                </nav>
            </div>

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
</body>
</html>