<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="lang" scope="session" />
<fmt:setBundle basename="property/authentication/signup/signUp" var="rb" />
<%@ page isELIgnored="false"%><html>
<head>
    <meta charset="UTF-8">
    <title>Final task</title>
    <link rel="stylesheet" href="stylesheets/bootstrap.css" />
    <link rel="stylesheet" href="stylesheets/bootstrap-theme.css" />
    <link rel="stylesheet" href="stylesheets/form_style.css">
    <link rel="stylesheet" href="stylesheets/social_icon.css">
    <link rel="stylesheet" href="stylesheets/styles.css">
</head>
<body class="main-body">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="">Buber</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
        </ul>
        <div class="top-button">
            <button class="button-small" id="aut-btn" onClick="window.location='jsp/authentication/logIn.jsp'" >Sign in</button>
    </div>
</nav>

<div class="carousel-wrapper" id="hide_1">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img src="img/volvo.jpg" alt="Chicago">
            </div>
            <div class="item">
                <img src="img/bmw.jpg" alt="Los Angeles">
            </div>
            <div class="item">
                <img src="img/uber-black.jpg" alt="New York">
            </div>
        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<footer class="end_footer footer navbar-inverse">
    <div class="container">
        <div class="col-md-12">
            <div class="footer-description">
                <nav>
                    <a href="index.html">Buber home</a> |
                    <a id="load-href" href="#" >About us</a>
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
    <script src="lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="lib/bootstrap/bootstrap.js"></script>
</body>
</html>
